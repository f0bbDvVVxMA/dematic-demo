# Java application for Dematic

Created using Hibernate, H2 embedded database
Allows for quick book storage, update, access and total rpice calculation.

### Build/run instructions

* Clone the repository.
* Open the project with your preferred IDE.
* Under "Run/Debug Configurations" add a GlassFish Server.
* Select Server Domain
* Select artifact to deploy
* Start GlassFish Server

# POST

```/books```

```/antique```

```/science```

### Request body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```

### Response body example

```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```

# GET

## List of barcodes

```/books```

```/antique```

```/science```

### Response example

```
[
    {
        "barcode": [
            "2"
        ],
        "quantity": 5
    },
    {
        "barcode": [
            "4"
        ],
        "quantity": 15
    }
]
```

## Book's information

```/books/{barcode}```

```/antique/{barcode}```

```/science/{barcode}```

### Response example

```
{
    "author": "Book",
    "barcode": "4",
    "name": "Science",
    "pricePerUnit": 69.13,
    "quantity": 15,
    "scienceIndex": 5
}
```

## Total price of specific books

```/books/{barcode}/totalprice```

```/antique/{barcode}/totalprice```

```/science/{barcode}/totalprice```

### Response body example

```
{
    "totalprice": 1131.04
}
```

# PUT

```/books/{barcode}```

```/antique/{barcode}```

```/science/{barcode}```

### Request body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```

### Response body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```
