# Example custom OpenID Connect Identity provider based on MitreID

This is the minimal project that creates a new customised MitreID server with the following changes:
* own authentication (accepts any username and password)
* own source of user data (set some dummy constants) 
* a new scope "myscope"
* new claims "myclaim1" and "myclaim2" released fro the scope "myscope" 
