# StructuralSpec

## Retrieve large dataset from Github

###Install wget 
If  
```
brew install wget
``` 

does not work, try http://osxdaily.com/2012/05/22/install-wget-mac-os-x/

### Retrieve all matching projects via Github Archive API
Example: due to the [pagination limits of github search](https://developer.github.com/guides/traversing-with-pagination/), we use query followed by page number to retrieve the entire dataset.

```
wget "https://api.github.com/search/repositories?q=language:Java+stars:>2&sort=stars&order=desc&page=58"
```

Organization: user:<org>
Language: language:<language>

More information:
https://www.githubarchive.org
https://developer.github.com/v3/search/

### Parse json object and retrieve all project data

python parseGithubJson.py <repo.json> <output.sh>
sh <output.sh>

#### Remove all un-Java files
```
find  . -type f ! -name "*.java" -delete
```





