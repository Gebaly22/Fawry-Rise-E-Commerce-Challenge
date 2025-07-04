# Fawry-Rise-E-Commerce-Challenge
E-commerce system simulation implementing product management, cart operations, checkout process, and shipping handling — covering expiry, stock, and balance validations. Java-based solution with complete use cases.


This project is a Java-based simulation of an e-commerce system designed as part of the Fawry Rise Journey Full Stack Development Internship challenge. It models products, cart management, checkout processes, and shipping operations with various business rules.

---

## Features

- Define products with properties: name, price, quantity.
- Support for perishable products with expiration dates (e.g., Cheese, Biscuits) and non-perishable products (e.g., TV, Mobile).
- Shipping logic: shippable products provide weight; non-shippable products are handled accordingly.
- Customers can add products to the cart with quantity restrictions based on availability.
- Checkout validates:
  - Cart is not empty.
  - Customer has sufficient balance.
  - Products are in stock and not expired.
- On successful checkout, prints:
  - Order subtotal.
  - Shipping fees.
  - Total paid amount.
  - Customer balance after payment.
- Sends shippable items to `ShippingService` that processes product name and weight.
- Clear console output with shipment notice and checkout receipt.

---

## Assumptions and Notes

- Expiration dates are checked to disallow expired product purchases.
- Shipping fees are calculated based on total package weight.
- AI-generated code usage is not permitted; all code written manually.
- The solution covers edge cases such as empty carts, insufficient balance, and out-of-stock products.

---

## Usage Example

```java
// Example usage in main or test cases
cart.add(cheese, 2);
cart.add(tv, 3);
cart.add(scratchCard, 1);
checkout(customer, cart);
