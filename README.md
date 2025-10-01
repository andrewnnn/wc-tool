
# WC tool

Based on CodingChallenges WC challenge done in Java

https://codingchallenges.fyi/challenges/challenge-wc

## Build Project
`mvn clean install`

## Run Command
`java -cp target/classes org.example.CcWc <file>`

or 

`./ccwc.sh <file> `

## Run Tests
`mvn test`

## Features

bytes of a file
```
>./ccwc.sh -c test.txt
  342190 test.txt
```

lines in a file
```
>./ccwc.sh -l test.txt
    7145 test.txt
```

words in a file
```
>./ccwc.sh -w test.txt
   58164 test.txt
```

characters in a file
```
>./ccwc.sh -m test.txt
  339292 test.txt
```

no args default option outputs words, lines and bytes
```
>./ccwc.sh test.txt
    7145   58164  342190 test.txt
```

can even read from std io 
```
>cat test.txt | ./ccwc.sh -l
    7145
```
