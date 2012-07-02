@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.2' )

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

def https = new HTTPBuilder( 'http://localhost:8080/' )
https.request( GET, JSON ) {
  uri.path = '/techTwits/1/statuses/public_timeline.json'
  uri.query = [ count:'5', include_entities: 'true' ]
 
  headers.'User-Agent' = 'Mozilla/3.0 (Macintosh; I; PPC)'
 
  // response handler for a success response code:
  response.success = { resp, json ->
    println "HTTP Status: ${resp.status}"
    
    // parse the JSON response object:
    json.each { 
      println "  ${it.user.name} : ${it.text}"
    }
  }
 
  // handler for any failure status code:
  response.failure = { resp ->
    println "Unexpected error: ${resp.status} : ${resp.statusLine.reasonPhrase}"
  }
}

println "done"

