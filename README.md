# Java application for Dematic

Created using Hibernate, H2 embedded database

# POST

## Request body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```

```/books```

```/antique```

```/science```


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

## Total price of specific books

```/books/{barcode}/totalprice```

```/antique/{barcode}/totalprice```

```/science/{barcode}/totalprice```

# PUT

```/books/{barcode}```

```/antique/{barcode}```

```/science/{barcode}```

## Request body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```

## Response body example
```
{
    "barcode": "5",
    "name": "Antique",
    "author": "Author",
    "quantity": 15,
    "pricePerUnit":69.13
}
```