# StructuralSpec

## Retrieve large dataset from Github

###Install wget 
If  ```brew install wget''' does not work, try http://osxdaily.com/2012/05/22/install-wget-mac-os-x/
### Retrieve all matching projects via Github Archive API
Example:
```wget https://api.github.com/search/repositories?q=user:google language:Java&sort=stars&order=desc'''

Organization: user:<org>
Language: language:<language>

More information:
https://www.githubarchive.org
https://developer.github.com/v3/search/

### Parse json object and retrieve all project data

python parseGithubJson.py <repo.json> <output.sh>
sh <output.sh>





