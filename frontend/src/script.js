const login = async () => {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  if (username === "") username = "Empty";
  if (password === "") password = "Empty";

  const res = await fetch("http://localhost:8000/api/login", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: username + "," + password,
  });
  const data = await res.json();
  document.getElementById("status").innerText = data.status;

  if (document.getElementById("status").innerText == "Success") {
    localStorage.setItem("username", data.username);
    localStorage.setItem("password", data.password);
    localStorage.setItem("role", data.role);
    document.getElementById("status").className = "mb-10 text-green-400";
    window.setTimeout(function () {
      window.location.href = "./dashboard.html";
    }, 1000);
  }
  else document.getElementById("status").className = "mb-10 text-red-400";
};

const register = async () => {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let role = document.getElementById("roles").value;

  console.log(role);

  if (username === "") username = "Empty";
  if (password === "") password = "Empty";
  if (role === "Default") role = "Empty";

  const res = await fetch("http://localhost:8000/api/register", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: username + "," + password + "," + role
  });
  const data = await res.json();
  document.getElementById("status").innerText = data.status;
  if (document.getElementById("status").innerText == "Account created successfully") {
    document.getElementById("status").className = "mb-10 text-green-400";
    window.setTimeout(function () {
      window.location.href = "./login.html";
    }, 1000);
  }
  else document.getElementById("status").className = "mb-10 text-red-400";
};

const logout = () => {
  localStorage.removeItem("username");
  localStorage.removeItem("password");
  localStorage.removeItem("role");
  window.location.href = "./login.html";
};

const setRestaurantButtonRole = () => {
  if (localStorage.getItem("role") == "Customer") {
    document
      .getElementById("restaurantButton")
      .setAttribute("href", "./restaurant1_customer.html");
  }
  if (localStorage.getItem("role") == "Restaurant Owner") {
    document
      .getElementById("restaurantButton")
      .setAttribute("href", "./restaurant1_owner.html");
  }
};

const getMenuOwner = async (restaurant) => {
  const container = document.querySelector("#menuContainer");
  const res = await fetch("http://localhost:8000/api/getMenu", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: restaurant,
  });
  const data = await res.json();
  console.log(data);
  data.menu.forEach((item) => {
    const name = document.createElement("h1");
    name.className = "text-lg font-semibold mb-3";
    name.textContent = item.name;
    container.appendChild(name);

    const mainDiv = document.createElement("div");
    mainDiv.className = "grid grid-cols-2 mb-10";
    container.appendChild(mainDiv);

    const descDiv = document.createElement("div");
    descDiv.className = "col-span-1";
    mainDiv.appendChild(descDiv);

    const description = document.createElement("p");
    description.className = "text-xs";
    description.textContent = item.description;
    descDiv.appendChild(description);

    const optionsDiv = document.createElement("div");
    optionsDiv.className = "col-span-1 flex justify-end items-center";
    mainDiv.appendChild(optionsDiv);

    const editButton = document.createElement("button");
    editButton.className =
      "mr-5 border-2 rounded-xl bg-white p-2 hover:bg-neutral-100 ease-linear duration-100";
    editButton.textContent = "Edit";
    editButton.onclick = () => {
      localStorage.setItem("editItemName", item.name);
      localStorage.setItem("editItemDescription", item.description);
      window.location.href = "./edit_menu_item.html";
    }
    optionsDiv.appendChild(editButton);

    const removeButton = document.createElement("button");
    removeButton.className =
      "border-2 rounded-xl bg-white p-2 hover:bg-neutral-100 ease-linear duration-100";
    removeButton.textContent = "Remove From Menu";
    removeButton.onclick = () => {
      localStorage.setItem("removeItemName", item.name);
      window.location.href = "./remove_menu_item.html";
    }
    optionsDiv.appendChild(removeButton);
  });
};

const addMenuItem = async () => {
  let name = document.getElementById("itemName").value;
  let description = document.getElementById("itemDescription").value;

  console.log(description);

  if (name === "") name = "Empty";
  if (description === "") description = "Empty";

  console.log(description);

  const res = await fetch("http://localhost:8000/api/addItem", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: name + "," + description
  });
  const data = await res.text();
  document.getElementById("status").innerText = data;
  if (document.getElementById("status").innerText == "Item added successfully") {
    document.getElementById("status").className = "text-green-400";
    window.setTimeout(function () {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  }
  else document.getElementById("status").className = "text-red-400";
}

const editMenuItem = async () => {
  const oldName = localStorage.getItem("editItemName");
  let newName = document.getElementById("newItemName").value;
  let newDescription = document.getElementById("newItemDescription").value;

  if (newName === "") newName = "Empty";
  if (newDescription === "") newDescription = "Empty";

  const res = await fetch("http://localhost:8000/api/editItem", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: oldName + "," + newName + "," + newDescription
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (document.getElementById("status").innerText == "Item edited successfully") {
    document.getElementById("status").className = "text-green-400";
    localStorage.removeItem("editItemName");
    localStorage.removeItem("editItemDescription");
    window.setTimeout(function () {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  }
  else document.getElementById("status").className = "text-red-400";
}

const removeMenuItem = async () => {
  const name = localStorage.getItem("removeItemName");

  const res = await fetch("http://localhost:8000/api/removeItem", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: name
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (document.getElementById("status").innerText == "Item removed successfully") {
    document.getElementById("status").className = "text-green-400";
    localStorage.removeItem("removeItemName");
    window.setTimeout(function () {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  }
  else document.getElementById("status").className = "text-red-400";
}