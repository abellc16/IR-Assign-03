# IR-Assign-03
IR Programming Assignment 03  

## 1 Assignment Goal  
Demonstrate your expertise in developing a search solution using Lucene, which is an
Information Retrieval (IR) library.  

## 2 Document Corpus  
The corpus will consists of thousands of bibliography documents. The documents
have the following general structure:  
  ```
  @article{Gudivada-2016-Renaissance-in-Database-ManagementNavigating-the-Landscape-of-Candidate-Systems,
    author = {V. Gudivada and D. Rao and V. Raghavan},
      title = {Renaissance in Database Management: Navigating
        the Landscape of Candidate Systems},
      journal = {IEEE Computer},
      year = {2016},
      month = apr,
      pages = {31 - 42},
      volume = {49},
      number = {4},
      doi = {10.1109/MC.2016.115},
      keywords = {Data Management, NoSQL, Scalability},
      abstract = {The recent emergence of a new class of systems
      for data management has challenged the well-entrenched
      relational databases. These systems provide several choices
      for data management under the umbrella term NoSQL. Making
      a right choice is critical to building applications that
      meet business needs. Performance, scalability and cost are
      the principal business drivers for these new systems. By design,
      they do not provide all of the relational database features such
      as transactions, data integrity enforcement, and declarative
      query language. Instead, they primarily focus on providing near
      real-time reads and writes in the order of billions and millions,
      respectively. This paper provides a unified perspective, strengths
      and limitations, and use case scenarios for the new data
      management systems.},
}
```
This structure ideally suits the structure of Lucene documents. Lucene treats each
document as a set of (field, value) pairs. The fields in the above bibliography document
are: author, title, journal, year, month, pages, volume, number, and abstract. There is
no order to the fields and may occur in any order. Field names are not case-sensitive.  
The unique key of the bibliography document is: Gudivada-2016-Renaissance-inDatabase-Management-Navigating-the-Landscape-of-Candidate
Systems. It is formed by concatenating the following elements: first author’s last name, year of publication,
and the title of the publication. Since spaces are not allowed in the unique key, for
readability purpose they are replaced by a hyphen/dash character.  
Bibliography documents can be of different kinds such as journalarticles, books, papers in conference proceedings. The notation @article indicates that the document is a journal publication.  

## 3 Data Preprocessing  
Data parsing and preprocessing are required before you can index bibliography documents using Lucene. Here are some issues to consider:
-  **Document key:** For the bibliography document of Section 2, the document key
is: Gudivada-2016-Renaissance-in-Database-Management- Navigating-the-Landscapeof-Candidate-Systems. However, some bibliography documents may not provide
which conforms to the notation discussed for forming keys (see Section 2). In
such cases, you need to create the key for the document.  
- **Author names list:** Author names are connected by the logical connective and
and there are no commas separating one name from another. This applies when
names are written in the following format: first_name optional_middle_name
last_name. However, names can also appear in this format: last_name, first_name
optional_middle_name. Note the comma after the last name. Furthermore, some
last names can be two words as in: De Jong.  
- **Field values:** Filed values may be specified in more than one way. For example,
the field value is delimited by { and } in: volume = {49}. Numeric field values
can be specified without { and } as in: volume = 49. Lastly, field values can be
delimited by " and " as in: keywords = "Data Management, NoSQL, Scalability"  
- **Keywords field values:** In some bibliography documents, keywords field values appear as in: keywords = Data Management, NoSQL, Scalability. However,
in some other documents, they may appear as: keywords = DataManagement,
NoSQL, Scalability. Note that the space delimiter is removed for multi-word keywords (i.e., DataManagement). You need to choose one of the two representations for consistency. If you choose to use space as a delimiter for multi-word
keywords, use a word segmentation algorithm to split DataManagement into
Data and Management. This has implications for how users query on the keywords field. Without the space delimiter, the documents in which the keyword
DataManagement appears can be found using a term query; otherwise, a phrase
query should be used.
- **Analyzing field values:** Values of some fields such as doi should not be analyzed, whereas the keywords field values may be analyzed. For each field, analysis decision must be made  
- **Storing field values:** For each field, you need to decide on whether or not to
store the field value as part of the Lucene index. If you store the field value,
index size increase. However, the field value is readily available for inclusion
in query snippets. Otherwise, a separate memory/disk access is required to the
bibliography document stored elsewhere.  

The above lists most common issues that you need to consider for parsing and
preprocessing. As you look at a sampling of the bibliography data, you may identify
additional issues for preprocessing.  

## 4 Search Solution Functionality  
Your solution should support the following query types:  
ur solution should support the following query types:
1. TermQuery  
2. BooleanQuery  
3. WildcardQuery      
4. PhraseQuery  
5. PrefixQuery  
6. MultiPhraseQuery  
7. FuzzyQuery  
8. RegexpQuery  
9. TermRangeQuery  
10. DisjunctionMaxQuery  
11. MatchAllDocsQuery  

Note that not all of the above query types can be executed on all fields. For example, TermRangeQuery can be executed on fields that have numeric (e.g., year of
publication) or string (e.g., publication month - apr) values.

## 5 Resources Provided  
1. Bibliography dataset in the order of thousands. The overall structure will be
similar the one shown in Section 2, but the required and optional fields will
vary according the bibliography type: @article, @book, @conference, @inbook,
@incollection, @mastersthesis, @phdthesis, · · ·
2. A sample Java program which illustrates how to create and index a Lucene document.
3. A sample Java program which illustrates construction and execution of Boolean
queries.
4. A sample Java program which illustrates how Lucene computes similarity/relevance score for a (document, query) pair.

## 6 Solution Steps  
Following are high-level solution steps. You may need to make several decisions in
each step related to low-level implementation details. Think about alternatives, articulate their pros and cons, reason about their algorithmic correctness and efficiency,
and make informed decisions. You are strongly encouraged to hold one or two brainstorming sessions with your team members to strategize a solution before you delve
into code-level details. Take an incremental approach. Please do not assume anything,
ask for clarification by posting on Piazza.  
1. Download the Bibliography corpus and familiarize with its structure and content.
2. Read one Bibliography record at a time, parse field values, and use them to
create and index the corresponding Lucene document.
3. Develop a simple user interface (command-line based interface is just fine) to
prompt the user for a query. Execute the query and display results.
4. Design test cases and execute them. Document test case execution results.
5. Submit your source code files, instructions for compiling and running your program, and documentation of your test cases and their execution results







