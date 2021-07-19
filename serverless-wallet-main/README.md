Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: MIT-0

This document from Amazon.com was enhanced for better implementation by James Thomas DSouza.

# serverless-wallet

This project will deploy sample code to demonstrate a wallet service using serverless technologies on AWS.
This deployment will include:
5 REST APIs on API Gateway
Supporting Lambda Functions
QLDB Ledger
QLDB Ledger stream and Kinesis Data Stream
DynamoDB Table
Supporting IAM roles

Please see the following [architecture diagram](readme-architecture.png)
It is recommended that deployment steps are carried out from a EC2 machine in a region that supports all services. Singapore has all AWS services.


Requirements:

Before proceeding with CDK deployment, make sure all softwares are installed as per steps given in HowtoDeployWalletService.pdf document

- GIT
- NodeJS
- Docker
- Python 3.8
- AWS CLI 
- AWS CDK CLI
- AWS CDK Python Library 

## Installation instructions

1. Clone or download the repository
2. Move to the `src/` directory and install Python pre-requisites:
   `	`
3. Modify `config.py_sample` and rename to `config.py` if necessary. Make sure the log_level setting is 'DEBUG' else Lambda will throw error. Also AWS Account number and region setting has to be updated in this file.

4. Deploy using the AWS CDK: `cdk deploy`
   If prompted, bootstrap the CDK using `cdk bootstrap` and then run the `deploy` command again
   Once the deployment is finished, see the output section for API endpoints.


## Post-deployment setup
1. Create the QLDB table. You may use the QLDB query editor on the Amazon QLDB Console to execute these queries. The table name must match the 'qldb_table_name' parameter in config.py:
   -- `CREATE TABLE "<qldb_table_name>"`

2. Create an index on the table for the `accountId` attribute:
   -- `CREATE INDEX ON "<qldb_table_name>" (accountId)`


## API Parameters:

All APIs must be called using the POST method. The **body** of the request must be a JSON object with the following attributes:

getFunds: `{ "accountId": "<accountId>" }`
getTransactions: `{ "accountId": "<accountId>" }`
createAccount: `{ "accountId": "<accountId>" }`
withdrawFunds: `{ "accountId": "<accountId>", "amount": <number> }`
addFunds: `{ "accountId": "<accountId>", "amount": <number> }`

You can test this via Postman software or simple front end html page on your local box. The example of html front end is inside the html folder

