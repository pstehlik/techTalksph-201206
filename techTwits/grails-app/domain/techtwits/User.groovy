package techtwits

class User {
    String name
    String email
    
    static constraints = {
      email(blank:false)
      name(blank:false)      
    }
    
    String toString(){
      "${name} - ${email}"
    }
}

