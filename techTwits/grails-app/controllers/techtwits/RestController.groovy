package techtwits

import grails.converters.deep.*

class RestController {

    def timelineAsJson = {
      def twits = Twit.list(max:params.count)
      
      render twits as JSON
    }
}
