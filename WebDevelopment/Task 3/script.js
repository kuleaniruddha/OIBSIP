 function convertTemperature() {
  const input = document.getElementById("tempInput").value;
  const from = document.getElementById("fromUnit").value;
  const to = document.getElementById("toUnit").value;
  const result = document.getElementById("result");

  if (input === "") {
    result.textContent = "Please enter a value.";
    return;
  }

  let temp = parseFloat(input);
  if (isNaN(temp)) {
    result.textContent = "Invalid number!";
    return;
  }

  let converted;

  // Convert from 'from' to Celsius first
  switch (from) {
    case "Fahrenheit":
      temp = (temp - 32) * 5 / 9;
      break;
    case "Kelvin":
      temp = temp - 273.15;
      break;
  }

  // Convert from Celsius to 'to'
  switch (to) {
    case "Fahrenheit":
      converted = (temp * 9 / 5) + 32;
      break;
    case "Kelvin":
      converted = temp + 273.15;
      break;
    case "Celsius":
      converted = temp;
      break;
  }

  result.textContent = `Converted Temperature: ${converted.toFixed(2)} °${unitSymbol(to)}`;
}

function unitSymbol(unit) {
  switch (unit) {
    case "Celsius": return "C";
    case "Fahrenheit": return "F";
    case "Kelvin": return "K";
  }
}
