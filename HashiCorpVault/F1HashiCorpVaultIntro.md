# HashiCorp Vault

1. There are 2 modes ( Dev & Server )

## How to setup locally

1. Download the Linux Binary
1. Add it to env :

    ```bash
    PATH=$PATH:/home/syndicate/Documents/Tools/HashiCorpVault/vault_1.19.4_linux_amd64/
    ```
1. Check Vault Version 

    ```ps
    syndicate@syn-debian:~
    $ vault version
    Vault v1.19.4 (322786e236e268532e4b189845971ba67b5cbb23), built 2025-05-14T13:04:47Z
    ```

1. Start Server in Dev Mode

    ```ps
    $ ./vault server -dev
    ==> Vault server configuration:

    Administrative Namespace: 
                Api Address: http://127.0.0.1:8200
                        Cgo: 
    ```

1. Few important things are printed out like the `vault address,root token,etc`. Best to keep note.
1. So we need to export to 2 tokens. Get the value from the console.

    ```ps
    $ export VAULT_ADDR='http://127.0.0.1:8200'
    $ export VAULT_TOKEN='hvs.UCUm3y......'
    ```

1. Run Vault Status for Good Measure.

    ```ps
    $ vault status
    Key             Value
    ---             -----
    Seal Type       shamir
    Initialized     true
    Sealed          false
    Total Shares    1
    Threshold       1
    Version         1.19.4
    Build Date      2025-05-14T13:04:47Z
    Storage Type    inmem
    Cluster Name    vault-cluster-d288ea60
    Cluster ID      a3b213f7-e976-2de3-446e-9f3b70cfe609
    HA Enabled      false
    ```
## Crud Ops using Command Line

### Enable Path

1. We define a custom path where our secrets will be stored. If we have a new custom path. We need to enable the new custom path into the secret engine.

    ```ps
    $ vault secrets enable -path=auto kv
    Success! Enabled the kv secrets engine at: auto/
    ```

### Write 

1. Put

    ```ps
    $ vault kv put auto/path my-key-1=value-1
    Success! Data written to: auto/path
    ```

### Read

1. Read all

    ```ps
    $ vault kv get auto/path
    ====== Data ======
    Key         Value
    ---         -----
    my-key-1    value-1
    ```

1. Read in JSON

    ```ps
    $ vault kv get -format=json auto/path
    {
    "request_id": "3563b578-8967-042d-a193-53a62708e411",
    "lease_id": "",
    "lease_duration": 2764800,
    "renewable": false,
    "data": {
        "my-key-1": "value-1"
    },
    "warnings": null,
    "mount_type": "kv"
    }
    ```

1. 