# API SHACLEX

REST API for [SHACLEX](https://github.com/labra/shaclex/) library. 

[![Build Status](https://travis-ci.org/labra/apiShaclex.svg?branch=master)](https://travis-ci.org/labra/apiShaclex)

# Methods

Check if a Schema is well formed and convert its syntax if needed

## Check schema

```
GET/POST schema/check
```
with the following parameters:
* `schema`: String representing the schema
* `schemaFormat`: String representing the format of the Schema, typical values: `SHEXC` (SHEX Compact syntax), `SHEXJ` (ShEx codified as JSON), `TURTLE`, `RDF/XML`, ... 
* `schemaName`: Name of schema engine. Typical values: `SHEX`, `SHACL`, etc.
* `resultFormat`: Format of the result after serializing the schema. Same values as for `schemaFormat`.
 
## Check data

```
GET/POST data/check
```
with the following parameters:
* `data`: String representing the data
* `dataFormat`: String representing the format of the Schema, typical values: `TURTLE`, `RDF/XML`, ... 
* `resultFormat`: Format of the result after serializing the data. Same values as for `dataFormat`.

## Validate data

```
GET/POST validate
```
with the following parameters:
* `data`: String representing the data
* `dataFormat`: String representing the format of the Schema, typical values: `TURTLE`, `RDF/XML`, ... 
* `resultFormat`: Format of the result after serializing the data. Same values as for `dataFormat`.
* `schema`: String representing the schema
* `schemaFormat`: String representing the format of the Schema, typical values: `SHEXC` (SHEX Compact syntax), `SHEXJ` (ShEx codified as JSON), `TURTLE`, `RDF/XML`, ... 
* `schemaName`: Name of schema engine. Typical values: `SHEX`, `SHACL`, etc.

Authors
=======

* [Jose Emilio Labra Gayo](http://www.di.uniovi.es/~labra)





