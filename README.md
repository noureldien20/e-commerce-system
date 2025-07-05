## Shipping Assumptions
- Shipping fees are calculated based on total weight of shippable items.
- Fixed rate of 5 currency units per kg is used as the shipping cost.
- Shipping is handled by a simple ShippingService that just prints item names and weights to the console.

## Design Assumptions
- No persistent storage is used — all data is held in memory.
- No concurrency or multi-user support is implemented.

## Product List used in Test (Main.java)

| **Product Name**     | **Type**                             | **Price** | **Quantity** | **Expiry Date**           | **Shipping Weight** |
|----------------------|--------------------------------------|----------:|--------------:|----------------------------|---------------------:|
| Cheese               | Expirable & Shippable                | 10.0      | 5             | `Today + 3 days`           | 1.5 kg               |
| TV                   | Non-Expirable & Shippable            | 500.0     | 10            | N/A                        | 10.0 kg              |
| Mobile Card          | Non-Expirable & Non-Shippable        | 5.0       | 10            | N/A                        | N/A                  |
| Expired Milk         | Expirable & Shippable                | 3.0       | 5             | `Today - 1 day`            | 1.0 kg               |
| Laptop               | Non-Expirable & Shippable            | 800.0     | 3             | N/A                        | 2.5 kg               |
| Gift Card            | Non-Expirable & Non-Shippable        | 25.0      | 20            | N/A                        | N/A                  |
| Expired Medicine     | Expirable & Non-Shippable            | 15.0      | 10            | `Today - 2 days`           | N/A                  |
