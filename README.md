# Major Tom

[![Travis CI](https://img.shields.io/travis/com/StarChart-Labs/major-tom.svg?branch=master)](https://travis-ci.com/StarChart-Labs/major-tom)

AWS Lambda which monitors for SNS messages and posts notifications to Slack

## Contributing

Information for how to contribute to Chronicler can be found in [the contribution guidelines](./docs/CONTRIBUTING.md)

## Legal

Major Tom is distributed under the [MIT License](https://opensource.org/licenses/MIT). There are no requirements for using it in your own project (a line in a NOTICES file is appreciated but not necessary for use)

The requirement for a copy of the license being included in distributions is fulfilled by a copy of the [LICENSE](./LICENSE) file being included in constructed JAR archives

## Reporting Vulnerabilities

If you discover a security vulnerability, contact the development team by e-mail at `vulnerabilities@starchartlabs.org`

## Use

StarChart-Labs AWS deployments use "Major Tom" by posting messages using the models in the `event-model` project to AWS SNS topics. The topic name for a given deployment is stored in an SSM parameter "/${self:provider.stage}/major-tom/aws/sns-topic-arn"
These messages are intended to be turned into notifications within the organization's Slack channel(s)
