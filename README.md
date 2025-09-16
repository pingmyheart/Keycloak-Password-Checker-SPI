# KEYCLOAK-PASSWORD-CHECKER-SPI

*Keycloak 20+ user credential checker plugin*

![Last Commit](https://img.shields.io/github/last-commit/pingmyheart/Keycloak-Password-Checker-SPI)
![Repo Size](https://img.shields.io/github/repo-size/pingmyheart/Keycloak-Password-Checker-SPI)
![Issues](https://img.shields.io/github/issues/pingmyheart/Keycloak-Password-Checker-SPI)
![Pull Requests](https://img.shields.io/github/issues-pr/pingmyheart/Keycloak-Password-Checker-SPI)
![License](https://img.shields.io/github/license/pingmyheart/Keycloak-Password-Checker-SPI)
![Top Language](https://img.shields.io/github/languages/top/pingmyheart/Keycloak-Password-Checker-SPI)
![Language Count](https://img.shields.io/github/languages/count/pingmyheart/Keycloak-Password-Checker-SPI)

# 🚀 Why this project?

In keycloak, there is no built-in feature to check if a user's credentials are correct without actually logging in. This
can be problematic in scenarios where you need to verify a user's credentials against a change password for example. To
address this issue, we have developed a custom SPI (Service Provider Interface) that allows you to check if a user's
credentials are correct without logging them in.

# ⚙️ Installation

1. Download the latest release from the release page
2. Copy the jar file to the `providers` directory of your Keycloak installation &rarr; `path/to/keycloak/providers`
3. Run the following command to install the provider:
   ```
   bin/kc.sh build
   bin/kc.sh start
   ```

# 🔨 Usage

1. Call the following endpoint to check if a user's credentials are correct:
   ```
    POST /realms/{realm}/credential-checker/check
    Content-Type: application/json
    
    {
        "username": "alice",
        "password": "mypassword"
    }
   ```

Available responses are:

- `200 OK`: The credentials are correct
- `401 Unauthorized`: The credentials are incorrect