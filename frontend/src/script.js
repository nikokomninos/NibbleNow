const test = async () => {
  const res = await fetch("http://localhost:8000/api/getTest", {
    method: "GET",
    mode: "cors",
  });
  return await res.text();
};

const login = async () => {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const res = await fetch("http://localhost:8000/api/login", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: username + "," + password
  })
  document.getElementById("status").innerText = await res.text();
};
