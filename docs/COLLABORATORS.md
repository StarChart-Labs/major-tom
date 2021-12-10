## Release Process

* Update the version number to remove the "-SNAPSHOT" designation. All version numbers should be a fully-qualified semantic version of form `<major>.<minor>.<micro>`
* Change the header "Unreleased" in CHANGE_LOG.md to the target release number, and create a new "Unreleased" header above it
* Run a full build via `./gradlew clean build`
  * If there are any errors, stash the changes to the version number and changelog until the issue can be corrected and merged to master as a separate commit/issue
* Perform a test deployment to the "dev" site via `serverless deploy` (Ensure you first perform the one-time setup below if this is your first deployment)
* Commit the version number and CHANGE_LOG updates
* Tag the git repository with the fully-qualified semantic version number
* Push changes and tag to GitHub
* Run the "Publish" GitHub Actions workflow
* Verify artifacts are present on Maven central in the [Staging repositories](https://oss.sonatype.org/#stagingRepositories) (login required), "close" it, and then "release" it
* Deploy to the production site via `serverless deploy --stage production`
* Change version number to `<released version> + 1 micro` + `-SNAPSHOT`
* Commit to git
* Push changes to GitHub
* Create a release on GitHub
* Change the `next-release` milestone to the released version number, move any unresolved tickets/pull-requests to a new `next-release` milestone, and close the version'd milestone

# Deployment

Deployment of Major Tom is done in the context of a "stage". A "stage" is something like "dev", "test", or "production" - a distinct environment where a full copy of the application will be independently deployed and run.

# One Time Setup

First, it is necessary to install `serverless` and then the plug-ins needed for major-tom

- [Install serverless](https://www.serverless.com/framework/docs/providers/aws/guide/installation/)
    - This includes configuring serverless keys for AWS
- Install the below plugins via [serverless plugin install](https://www.serverless.com/framework/docs/providers/aws/cli-reference/plugin-install/)

To deploy Major Tom for a given stage, the following manual setup is required once:

- On [AWS System Manager](https://console.aws.amazon.com/systems-manager), login with an admin StarChart-Labs account and create the following values in Parameter Store:
  - /${stage}/major-tom/slack/url
    - The Slack webhook URL (Created under Slack Apps -> Incoming Webhooks)
