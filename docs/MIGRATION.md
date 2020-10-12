# Major Version Migrations

This details steps for migrating between major versions of Major-Tom, which may contain breaking changes

## 0.x to 1.x

### May be Completed Prior to Upgrade

- Ensure any dependencies on `com.google.code.findbugs:jsr305` are fulfilled directly (major-tom no longer pulls this in as a transitive dependency when used in projects) (GH-5)