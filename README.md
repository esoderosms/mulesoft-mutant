# Autor
Emanuel Sodero

# Challenge Mulesoft
The challenge consists of detecting 4 consecutive letters in a string array that way it is determined if the DNA belongs to a mutant or a human.

## Installation
How to install it
```
1) git clone https://github.com/esoderosms/mulesoft-mutant.git
2) cd mulesoft-mutant
3) mvn install
```
## How to use Api Rest
Execute in terminal
```
curl --location --request GET 'http://localhost:8080/dna/stats' 

Mutant
curl --location --request POST 'http://localhost:8080/dna/mutant' -H "Content-Type:application/json" -d '{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] 
} ​'

Human
curl --location --request POST 'http://localhost:8080/dna/mutant' -H "Content-Type:application/json" -d '{
"dna":["ATGCGA","CAGTGC","TTATAT","AGAAGG","CCBCTA","TCACTG"] 
}'
```