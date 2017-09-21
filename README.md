# Example custom OpenID Connect identity provider based on MitreID

This is a minimal project that creates a new customised MitreID server with the following changes:
* own authentication (accepts any username and password)
* own source of user data (sets some dummy constants) 
* a new scope "myscope"
* new claims "myclaim1" and "myclaim2" released for the scope "myscope" 
