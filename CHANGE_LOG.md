# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]
### Changed
- Switched from psuedo-parameters serverless plugin to native region/accountId variable substitution
- Updated com.amazonaws:aws-java-sdk-cloudwatch from 1.11.797 to 1.12.52
- Updated com.amazonaws:aws-java-sdk-sns from 1.11.797 to 1.12.52
- Updated com.amazonaws:aws-java-sdk-ssm from 1.11.797  to 1.12.52
- Updated com.amazonaws:aws-lambda-java-events from 3.1.0 to 3.9.0
- Updated com.amazonaws:aws-lambda-java-log4j2 from 1.2.0,implementation
- Updated override of com.fasterxml.jackson.core:jackson-databind from 2.11.0 to 2.12.4
- Updated com.google.code.gson:gson from 2.8.6 to 2.8.8
- Updated com.squareup.okhttp3:okhttp from 4.8.0 to 4.9.1
- Updated org.apache.logging.log4j:log4j-core from 2.13.3 to 2.14.1
- Updated org.apache.logging.log4j:log4j-slf4j-impl from 2.13.3 to 2.14.1
- Updated org.starchartlabs.alloy:alloy-core from 1.0.0 to 1.0.1
- Updated org.starchartlabs.machete:machete-sns from 1.0.0 to 1.0.1
- Updated org.starchartlabs.machete:machete-ssm from 1.0.0 to 1.0.1
- Updated org.mockito:mockito-core from 3.3.3 to 3.12.1
- Updated org.slf4j:slf4j-api from 1.7.30 to 1.7.32
- Updated org.slf4j:slf4j-simple from 1.7.30 to 1.7.32
- Updated org.testng:testng from 7.3.0 to 7.4.0

## [1.0.0]
### Changed
- Updated AWS dependencies to 1.11.797
- Updated jackson databind to 2.11.0
- Updated OkHttp to 4.8.0
- Updated Alloy to 1.0.0
- Updated Machete to 1.0.0
- Add Checkstyle code quality validation to build process
- Add automated validation of copyright statements
- Switch dependency on `com.google.code.findbugs:jsr305` to compileOnly to avoid pulling in as a transitive dependency to consuming projects

## [0.2.2]
### Changed
- Updated dependencies to latest bugfix version

## [0.2.1]
### Changed
- Updated dependencies to latest bugfix versions

## [0.2.0]
### Changed
- Setup upload of notification topic ARN to AWS SSM parameter store upload release

## [0.1.0]
### Added
- Flow for receiving define events from AWS SNS and posting the indicated message to Slack
