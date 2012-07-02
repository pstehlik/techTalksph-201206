package techtwits

class Twit {
    String text
    User user
    
    static belongsTo = [user:User]
    static constraints = {
      text(blank:false, maxSize:140)
    }
}
