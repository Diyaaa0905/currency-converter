# Currency Converter – Java Console App

A simple but fully interactive and extensible **console-based currency converter** written in Java. The app supports **real-time currency conversion**, **dynamic currency addition/removal**, and a built-in **undo/redo history stack** for tracking conversions.

#### *NOTE: This project was made to re-inforce concepts of Data Structure and Algorithms.*
---

## Features

- **Convert** between dozens of global currencies
- **Undo/Redo** support for conversion operations
- **Add** new currencies dynamically during runtime
- **Remove** unsupported or outdated currencies
- **View** full conversion history
- **Auto-switch** feature to reverse conversions instantly
- Built-in currency validation and error handling
- Intelligent user prompts and flow control

---

## How It Works

- **Base currency is USD**. All rates are stored relative to USD.
- User provides source currency, target currency, and amount.
- System checks currency validity and performs the conversion.
- A record is created and added to the conversion history.
- Users can undo or redo any conversion as needed.

---

## Project Structure

```bash
CurrencyConverter/
├── Main.java                # Entry point of the program
├── Currency.java            # Represents a single currency
├── CurrencyStore.java       # Stores and manages all currencies
├── Conversion.java          # Represents a single conversion operation
├── ConversionManager.java   # Handles history, undo, redo
└── README.md                # You are here!
```

---

## Technologies

- Java 8+
- BigDecimal for precision
- Stack, ArrayList, and HashMap for internal data management
- Console I/O using Scanner

---

## To Do / Future Enhancements
- Integrate live exchange rates via an API (e.g., OpenExchangeRates)
- Add GUI with JavaFX or Swing
- Save and load conversion history from a file
- Support for currency symbols (€, $, ₹, etc.)
- Language localization support

---

## Feedback
This project is open to feedback, suggestions, and contributions.

Feel free to open an issue or submit a pull request!

---

## --License--
This project is licensed under the MIT License – see LICENSE.md for details.

<h1 align="center">== ⋅˚₊‧ ୨୧ ‧₊˚⋅ ==</h1>
