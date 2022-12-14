# Changelog - Deliveryman

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.1-SNAPSHOT] - 2023-01-07

### Added
- Better logging.

## [1.2.0-SNAPSHOT] - 2023-01-03

### Added
- NGINX ingress controlles for Kubernetes.
- GraphQL support.
- Metrics.
- Logging.

## [1.1.0-SNAPSHOT] - 2022-11-22

### Added
- Health checking.
- Config reader.
- Manual breaking with /demo endpoint.

## [1.0.0-SNAPSHOT] - 2022-11-15

### Added
- [api module](api) for API interface on /v1/deliveryman.
- [lib module](lib) for definition of Deliveryman metadata objects.
- [models module](models) for defining SQL entities.
- [services module](services) for business logic.
- [Github Actions](.github/workflows) for CI/CD.
- [k8s](k8s) for Kubernetes related files.
- Various files for Docker, Maven, and Github
