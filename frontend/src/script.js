/**
 * login:
 *
 * Logs a user in
 * Will redirect to dashboard page if successful
 * Otherwise, displays error
 * Stores username, password and role as local
 * storage variables
 *
 */
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
    window.setTimeout(() => {
      window.location.href = "./dashboard.html";
    }, 1000);
  } else document.getElementById("status").className = "mb-10 text-red-400";
};

/**
 * register:
 *
 * Fetch request for registering a new user.
 * Will redirect to login page if successful
 * Otherwise, displays error
 *
 */
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
    body: username + "," + password + "," + role,
  });
  const data = await res.json();
  document.getElementById("status").innerText = data.status;
  if (
    document.getElementById("status").innerText ==
    "Account created successfully"
  ) {
    document.getElementById("status").className = "mb-10 text-green-400";
    window.setTimeout(() => {
      window.location.href = "./login.html";
    }, 1000);
  } else document.getElementById("status").className = "mb-10 text-red-400";
};

/**
 * logout:
 *
 * Logs a user out.
 * Removes all variables from local storage
 *
 */
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

const setOrdersButtonRole = () => {
  if (localStorage.getItem("role") == "Customer") {
    document
      .getElementById("ordersButton")
      .setAttribute("href", "./customer_orders.html");
  }

  if (localStorage.getItem("role") == "Driver") {
    document
      .getElementById("ordersButton")
      .setAttribute("href", "./driver_orders.html");
  } else {
    document.getElementById("ordersButton").className("hidden");
  }
};

/**
 * buildMenuOwner:
 *
 * Gets the menu of a restaurant, for the owner view of said restaurant
 * @param {*} restaurant, the name of the restaurant
 */
const buildMenuOwner = async (restaurant) => {
  const container = document.querySelector("#menuContainer");
  const res = await fetch("http://localhost:8000/api/getMenu", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: restaurant,
  });
  const data = await res.json();
  console.log(data);
  // Builds DOM elements for each item on the menu
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
    };
    optionsDiv.appendChild(editButton);

    const removeButton = document.createElement("button");
    removeButton.className =
      "border-2 rounded-xl bg-white p-2 hover:bg-neutral-100 ease-linear duration-100";
    removeButton.textContent = "Remove From Menu";
    removeButton.onclick = () => {
      localStorage.setItem("removeItemName", item.name);
      window.location.href = "./remove_menu_item.html";
    };
    optionsDiv.appendChild(removeButton);
  });
};

/**
 * addMenuItem:
 *
 * Adds an item to the menu
 * Currently hardcoded to add to restaurant
 * named '500 Degrees'
 */
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
    body: name + "," + description,
  });
  const data = await res.text();
  document.getElementById("status").innerText = data;
  if (
    document.getElementById("status").innerText == "Item added successfully"
  ) {
    document.getElementById("status").className = "text-green-400";
    window.setTimeout(() => {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  } else document.getElementById("status").className = "text-red-400";
};

/**
 * editMenuItem:
 *
 * Edits an item on the menu
 * Currently hardcoded to edit items from restaurant
 * named '500 Degrees'
 */
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
    body: oldName + "," + newName + "," + newDescription,
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (
    document.getElementById("status").innerText == "Item edited successfully"
  ) {
    document.getElementById("status").className = "text-green-400";
    localStorage.removeItem("editItemName");
    localStorage.removeItem("editItemDescription");
    window.setTimeout(() => {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  } else document.getElementById("status").className = "text-red-400";
};

/**
 * removeMenuItem:
 *
 * Remove an item from the menu
 * Currently hardcoded to remove items from restaurant
 * named '500 Degrees'
 */
const removeMenuItem = async () => {
  const name = localStorage.getItem("removeItemName");

  const res = await fetch("http://localhost:8000/api/removeItem", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: name,
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (
    document.getElementById("status").innerText == "Item removed successfully"
  ) {
    document.getElementById("status").className = "text-green-400";
    localStorage.removeItem("removeItemName");
    window.setTimeout(() => {
      window.location.href = "./restaurant1_owner.html";
    }, 1000);
  } else document.getElementById("status").className = "text-red-400";
};

/**
 * buildMenuCustomer:
 *
 * Gets the menu of a restaurant, for the owner view of said restaurant
 * @param {String} restaurant, the name of the restaurant
 */
const buildMenuCustomer = async (restaurant) => {
  const container = document.querySelector("#menuContainer");
  const res = await fetch("http://localhost:8000/api/getMenu", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: restaurant,
  });
  const data = await res.json();

  // Builds DOM elements for each item on the menu
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

    const addButton = document.createElement("button");
    addButton.className =
      "mr-5 border-2 rounded-xl bg-white p-2 hover:bg-neutral-100 ease-linear duration-100";
    addButton.textContent = "Add To Cart";
    addButton.onclick = () => {
      addItemToCart(item.name, item.description);
    };
    optionsDiv.appendChild(addButton);
  });
};

/**
 * buildCart:
 *
 * Gets the cart of the logged in user
 */
const buildCart = async () => {
  const container = document.querySelector("#cartContainer");
  const res = await fetch("http://localhost:8000/api/getCart", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: localStorage.getItem("username"),
  });
  const data = await res.json();

  data.cart.forEach((item) => {
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

    const removeButton = document.createElement("button");
    removeButton.className =
      "mr-5 border-2 rounded-xl bg-white p-2 hover:bg-neutral-100 ease-linear duration-100";
    removeButton.textContent = "Remove From Cart";
    removeButton.onclick = () => {
      removeItemFromCart(item.name, item.description);
      window.setTimeout(() => {
        location.reload();
      }, 1500);
    };
    optionsDiv.appendChild(removeButton);
  });
};

/**
 * addItemToCart:
 *
 * Adds an item to the cart of the logged in user
 * @param {String} itemName
 * @param {String} itemDescription
 */
const addItemToCart = async (itemName, itemDescription) => {
  const res = await fetch("http://localhost:8000/api/addItemToCart", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body:
      localStorage.getItem("username") + "," + itemName + "," + itemDescription,
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (
    document.getElementById("status").innerText ==
    "Item added successfully to cart"
  ) {
    document.getElementById("status").className =
      "text-green-400 text-center mb-10";
  } else
    document.getElementById("status").className =
      "text-red-400 text-center mb-10";

  window.setTimeout(() => {
    document.getElementById("status").innerText = "Status";
    document.getElementById("status").className = "text-center mb-10";
  }, 2000);
};

/**
 * removeItemFromCart:
 *
 * Removes an item from the cart of the logged in user
 * @param {String} itemName
 * @param {String} itemDescription
 */
const removeItemFromCart = async (itemName, itemDescription) => {
  const res = await fetch("http://localhost:8000/api/removeItemFromCart", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body:
      localStorage.getItem("username") + "," + itemName + "," + itemDescription,
  });
  const data = await res.text();

  document.getElementById("status").innerText = data;
  if (
    document.getElementById("status").innerText ==
    "Item removed successfully from cart"
  ) {
    document.getElementById("status").className =
      "text-center mb-10 text-green-400";
  } else
    document.getElementById("status").className =
      "text-center mb-10 text-red-400";

  window.setTimeout(() => {
    document.getElementById("status").innerText = "Status";
    document.getElementById("status").className = "text-center mb-10";
  }, 2000);
};

/**
 * submitOrder:
 *
 * Submits the order for the logged in user
 */
const submitOrder = async () => {
  const res = await fetch("http://localhost:8000/api/submitOrder", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: localStorage.getItem("username"),
  });
  const data = await res.text();

  if (data == "Order submitted successfully") {
    alert("Order submitted successfully!");
    window.location.href = "./restaurant1_customer.html";
  } else alert("Error: Order not submitted");
};

/**
 * getOrders:
 *
 * Gets all orders for a restaurant
 * @param {String} restaurant
 */
const getOrders = async (restaurant) => {
  const container = document.querySelector("#ordersContainer");
  const res = await fetch("http://localhost:8000/api/getOrders", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "text/plain" },
    body: restaurant,
  });
  const data = await res.json();

  data.orders.forEach((order) => {
    const orderDiv = document.createElement("div");
    orderDiv.className =
      "flex flex-col p-5 ml-10 mr-10 w-[300px] border-2 rounded-3xl bg-[#F6F6FF] drop-shadow-lg hover:bg-gray-200 ease-linear duration-100";
    container.appendChild(orderDiv);

    const username = document.createElement("h1");
    username.className = "text-2xl font-semibold mb-3";
    username.textContent = order.username;
    orderDiv.appendChild(username);

    const itemsHeader = document.createElement("h2");
    itemsHeader.className = "text-lg mb-3";
    itemsHeader.textContent = "Items:";
    orderDiv.appendChild(itemsHeader);

    const itemsList = document.createElement("p");
    itemsList.className = "text-xs";
    itemsList.textContent = order.items.join(", ");
    orderDiv.appendChild(itemsList);
  });
};
