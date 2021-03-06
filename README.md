# Clustering and Structuring Code Examples

Given a code search query for a task, such as ‘add undo and redo actions in my Graphics Editor’, this tool queries code search engine, identifies common API calls that provide main feature across different partial program examples, and helps complete this code reuse task based on the  target context.
 
## Problem
Developers frequently use source code examples as the basis for interacting with an application programming interface (API) to obtain the needed functionality. In this case, the source code example serves as the explicit origin for a reuse task which transforms a sequence of APIs with corresponding control structure to the target context. During the process of reuse task such as ‘add undo and redo actions in my Graphics Editor’, developers often query for a set of examples considering the task, and integrate examples that match the task into their system.

Both location and integration of reusable examples are not trivial. 

For the phase of location, existing tools that search for specific API usage examples might not be able to support such reuse tasks that always involve in multiple classes and related methods. As a result, developers always query general search engines like Google or expert sites like StackOverflow to investigate potential tutorials for desired features. These search engines will return a list of informal documentations and users have to search for source code elements in these resources.

For the phase of integration, developers should not only measure structural and semantic similarity between the example and target context for the main API calls that provide desired features, but also determine which unnecessary code should be eliminated to save maintenance cost and which related elements should be transformed with structural correspondence to the target context. Unfortunately, for any selected example in non-trivial system, the number of structural dependencies to follow is much too large to be completely covered by developers. 

The partial programs extracted from the location phase make it even harder to tease out irrelevant elements without knowing a full scenario of precise type information and method implementation details. Moreover, the location and integration phase can be iterative as there is no guarantee that a selected example will be appropriate until the integration results are examined.

## Approach
To overcome these challenges, we propose to automatically identify common API calls that provide the main feature across different partial program examples from informal documentation, and integrate this reusable example to the target context with a set of related elements that are necessary to implement the main feature. 

We assume that the overlapping API calls are the main features that the user intends to reuse based on the user query, and the related elements should be transformed to the target together with main features if they are data dependent and control dependent on the common functionalities in a common way across different examples. 

Our tool invokes search engine (e.g., Google) to obtain a list of partial programs by recovering links between code elements and informal resources, clusters these examples and extracts common structural facts (e.g., jQuery logical facts) with corresponding control structures for each cluster, identifies a set of related elements that interact with main elements in a common way, constructs reuse task with both main elements and related elements and ranks them based on user’s context, and finally integrates the reuse task selected by users to the target context.
