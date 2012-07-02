import techtwits.*
class BootStrap {

    def init = { servletContext ->
      def usr0 = new User(name: 'philip', email:'p@pstehlik.com')
      def usr1 = new User(name: 'maex', email:'maex@maex.us')
      def usr2 = new User(name: 'chim', email:'chimster@yahoo.com')
      
      def t0 = new Twit(text:'doing grails demo', user:usr0)
      def t1 = new Twit(text:'doing groovy demo at techtalks', user:usr0)
      def t2 = new Twit(text:'demoing great software', user:usr1)
      def t3 = new Twit(text:'growing a beard', user:usr1)
      def t4 = new Twit(text:'studying cool stuff', user:usr2)
                        
      [usr0,usr1,usr2, t0,t1,t2,t3,t4].each{
        it.save(failOnError:true) 
      }
    }
    def destroy = {
    }
}
