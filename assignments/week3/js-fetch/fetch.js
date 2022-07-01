// document.getElementById('getData').onclick = getData;
// document.getElementById('getData').addEventListener("click", getData);

/*
    - When button is clicked, send http request to API for a specific id

    - get the id value from input box
    - send request to PokeAPI
        - Method: GET
        - Headers: None
        - Body: None
        - url: https://pokeapi.co/api/v2/pokemon/ + id from input box
    - might have to convert JSON to JS object

    - populate the data in Section
*/
let baseApiURL = 'https://pokeapi.co/api/v2/pokemon';

async function getData() {
    console.log('Button was clicked!');
    let id = document.getElementById('dataInput').value;
    console.log(`id = ${id}`);

    let httpResponse = await fetch(`${baseApiURL}/${id}`);

    if (httpResponse.status >= 200 && httpResponse.status < 300) {
        let data = await httpResponse.json();

        populateData(data);

    } else {
        console.log('Invalid request.');
    }
}

function populateData(response) {
    let section = document.getElementById("data");
    while (section.firstChild) {
        section.removeChild(section.firstChild);
    }

    let container = document.createElement("div");
    container.style.cssText = `
        display: flex;
        align-items: center;
        width: max-content;
    `;

    let div1 = document.createElement("div");
    let image = document.createElement("img");
    image.setAttribute("src", response.sprites.front_default);
    container.appendChild(div1);
    container.appendChild(image);

    let h = document.createElement("h1");
    h.innerHTML = response.name;
    h.style.margin = "0px";
    div1.appendChild(h);

    let div2 = document.createElement("div");
    div1.appendChild(div2);


    response.types.forEach(typeinfo => {
        let button = document.createElement("button");
        button.innerHTML = typeinfo.type.name;
        
        const colors = {
            normal: '#A8A77A',
            fire: '#EE8130',
            water: '#6390F0',
            electric: '#F7D02C',
            grass: '#7AC74C',
            ice: '#96D9D6',
            fighting: '#C22E28',
            poison: '#A33EA1',
            ground: '#E2BF65',
            flying: '#A98FF3',
            psychic: '#F95587',
            bug: '#A6B91A',
            rock: '#B6A136',
            ghost: '#735797',
            dragon: '#6F35FC',
            dark: '#705746',
            steel: '#B7B7CE',
            fairy: '#D685AD',
        };

        button.style.cssText = `
        border-radius: 4px;
        padding: 4px;
        margin-right: 4px;
        background-color: ${colors[typeinfo.type.name]}
    `;

        div2.append(button)
    }
    );

    section.appendChild(container);
}