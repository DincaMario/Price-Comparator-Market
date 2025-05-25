# PriceComparator 

This is a backend application written in **vanilla Java** for the **Accesa Java Internship 2025 Coding Challenge**. It simulates a real-world price comparator used to analyze and manage product pricing across multiple retail chains in Romania, such as Lidl, Kaufland, and Profi.

## What it does

The project processes CSV datasets of supermarket products and discounts, allowing users to:

- Browse products by name, brand, store, or price range.
- Track historical pricing and identify active or newly introduced discounts.
- View the best current discounts sorted by percentage.
- Simulate a shopping basket (add/remove/clear items).
- Make informed purchasing decisions by comparing "value per unit".

## How it was made

**Tech Stack:**
- **Java 17** – Core backend logic.
- **OpenCSV** – CSV parsing.
- **File I/O** – For dynamic data loading from local directories.
- **SimpleDateFormat + java.util.Date** – Handling discount date ranges.
- **Console Interface** – Interactive CLI built with `Scanner`.

**Architecture:**
- `Product` – Data model for item info and pricing.
- `Parser` – CSV reader that unifies products across multiple sources and dates.
- `Service` – Handles all business logic and user interactions.
- `Main` – Entrypoint that ties everything together.



## Features Implemented

This project successfully implements all **core** and several **bonus** features from the challenge:

### Shopping Basket
- Add products based on filter criteria.
- Remove individual items or clear the basket.
- See full list of added items.

### Product Filtering
- Search by **brand**, **name**, **store**, **price range**, or view all products.
- Full-text search via `Scanner`.

### Discount Analysis
- Show **best discounts** across stores, sorted by percentage.
- Show **newly introduced discounts** (within the last 24 hours).
- Display **discount duration** for each product.

### Value Optimization
- `price per unit` is calculated and stored (`ratio`) for future enhancements.
- `percentageChange` tracks the % of the price difference between two weeks (e.g., price increase or decrease between 2025-05-01 and 2025-05-08).

## Video Demonstration

https://youtu.be/PAGqwAHaKto

## Assumptions & Simplifications
- Only the latest version of a product (latest CSV date) is kept
- Discounts apply only if within `start_date` and `end_date`
- No frontend or DB persistence; all data is in-memory.

## Lessons Learned
- Clean separation of concerns via classes (`Product`, `Service`, `Parser`)
- Managing input/output with proper error handling
- Realistic handling of duplicate datasets and product merging
- Building fully working features without a framework

## How to Run
- Clone the repository
- Open project in IntelliJ (or any Java IDE)
- Make sure to include the `OpenCSV` library and `Apache Commons Lang`
- Run `Main.java`
