# coinbase-rest
A grails middleware application that wraps Coinbase (CB) APIs 

# Introduction
Coinbase Rest is a Grails wrapper for the [Coinbase v2 APIs](https://developers.coinbase.com/api/v2). 
Its not production ready, but is a good start for anyone looking to build Coinbase middleware with Grails or is looking for a Java reference to access CB APIs. 
This initial load contains Service implementaitons that access the main CB APIs: 
- Accounts
- Addresses
- Timestamp
- Transactions

Each service has a corresponding parsers to parse the CB response into groovy objects. The objects were designed based 
off the CB API documentation, which does not have a complete object definition for each type of response. This is especially true regarding the 
types of transaactions (buy, sell, transfer...) returned from Transactions APIs. The transaction objects are accurate based off the current 
documentation, but assumed are subject to change as CB matures. Transactions are complex object models and, at this time, are incomplete,
but its a very good start. 

The parsers and services have decent tests for basic use. Better tests are needed. 

**Note:** I was able to run grails test-app fine during the initial phases. Then, in typical grails fashion, I began to get ClassDefNotFound for hamcrest Matchers seemingly out of the blue. 
I have no idea why. This may need resolved or may work perfectly for you. Who knows with Grails dependency management. 

# Usage 
You will need a Coinbase account and create API access credentials. This application does not take OAuth into account yet and you will need to supply 
the credentials. Follow the coinbase instructions to do this. 

Once a CB API access key, secret and version are obtained you have 2 choices to set these values in order to call the CB API. All of the services 
read the values from the applications.yaml file and are configuration aware. In the application.yaml there is a coinbase section with the CB 
properties of wihch the access key, secre and version a part of. To set these: 
1. **Recommended/Default**: set System environment variables for COINBASE_ACCESS_KEY, COINBASE_VERSION, COINBASE_SECRET. This ensures that
these values are not hardcoded and are variables of your system. By default the application reads the system variables. Take note that you may 
need to set these variables in your IDE, such as Intellij IDEA, which may not get the env variables.
2. Hard code the values in application.yaml. This is not recommeneded, but will get the job done if your IDE or setup is not reading your system 
variables. 

Controllers are not defined yet, so this application does not have true endpoints, just services. This is mainly a reference implementation and 
a good template to jumpstart a Java or Groovy integration. 
