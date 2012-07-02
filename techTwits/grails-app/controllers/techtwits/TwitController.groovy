package techtwits

import org.springframework.dao.DataIntegrityViolationException

class TwitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [twitInstanceList: Twit.list(params), twitInstanceTotal: Twit.count()]
    }

    def create() {
        [twitInstance: new Twit(params)]
    }

    def save() {
        def twitInstance = new Twit(params)
        if (!twitInstance.save(flush: true)) {
            render(view: "create", model: [twitInstance: twitInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'twit.label', default: 'Twit'), twitInstance.id])
        redirect(action: "show", id: twitInstance.id)
    }

    def show() {
        def twitInstance = Twit.get(params.id)
        if (!twitInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "list")
            return
        }

        [twitInstance: twitInstance]
    }

    def edit() {
        def twitInstance = Twit.get(params.id)
        if (!twitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "list")
            return
        }

        [twitInstance: twitInstance]
    }

    def update() {
        def twitInstance = Twit.get(params.id)
        if (!twitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (twitInstance.version > version) {
                twitInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'twit.label', default: 'Twit')] as Object[],
                          "Another user has updated this Twit while you were editing")
                render(view: "edit", model: [twitInstance: twitInstance])
                return
            }
        }

        twitInstance.properties = params

        if (!twitInstance.save(flush: true)) {
            render(view: "edit", model: [twitInstance: twitInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'twit.label', default: 'Twit'), twitInstance.id])
        redirect(action: "show", id: twitInstance.id)
    }

    def delete() {
        def twitInstance = Twit.get(params.id)
        if (!twitInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "list")
            return
        }

        try {
            twitInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'twit.label', default: 'Twit'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
