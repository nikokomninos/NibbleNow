const test = () => {
  fetch("http://localhost:8000/api")
    .then((response) => response.text())
    .then((data) => {
      document.getElementById("status").innerText = data;
    })
    .catch((err) => console.error("Error: ", err));
};
