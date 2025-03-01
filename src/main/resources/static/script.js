document.addEventListener("DOMContentLoaded", () => {
    fetchOrders();

    document.getElementById("orderForm").addEventListener("submit", (event) => {
        event.preventDefault();
        createOrder();
    });
});

function fetchOrders() {
    fetch("http://localhost:8080/orders") // Henter ordrer fra backend
        .then(response => response.json()) // Konverterer til JSON
        .then(data => {
            const orderList = document.getElementById("order-list");
            orderList.innerHTML = ""; // Rydder listen før opdatering

            data.forEach(order => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>
                        <button onclick="deleteOrder(${order.id})">Slet</button>
                    </td>
                `;
                orderList.appendChild(row); // Tilføjer ordren til tabellen
            });
        })
        .catch(error => console.error("Fejl ved hentning af ordrer:", error));
}

// Kør fetchOrders() når siden indlæses
document.addEventListener("DOMContentLoaded", fetchOrders);


// Opretter en ny ordre
function createOrder() {
    const status = document.getElementById("status").value;

    fetch("/orders", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ status: status })
    })
        .then(response => response.json())
        .then(() => {
            document.getElementById("status").value = "";
            fetchOrders();
        })
        .catch(error => console.error("Fejl ved oprettelse af ordre:", error));
}

// Sletter en ordre
function deleteOrder(id) {
    fetch(`/orders/${id}`, {
        method: "DELETE"
    })
        .then(() => fetchOrders())
        .catch(error => console.error("Fejl ved sletning af ordre:", error));
}
