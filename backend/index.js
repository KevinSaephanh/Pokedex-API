const AWS = require('aws-sdk')
const fetch = require('node-fetch')
AWS.config.loadFromPath('./config.json')
const dynamodb = new AWS.DynamoDB()

// Deprecated

// gender -> del
// ability -> abilities
// type -> types
// weakness -> del

const addZero = (i) => (i < 10) ? ('0' + i) : i

function populatePokemonN(i) {

    return fetch(`https://pokeapi.co/api/v2/pokemon/${i}/`)
        .then(res => res.text())
        .then(body => {
            pokeObj = JSON.parse(body);

            let dbParams = {};
            dbParams.Item = {};
            dbParams.TableName = 'pokeapi'
            dbParams.ReturnConsumedCapacity = "TOTAL"

            // name 
            dbParams.Item.name = { S: pokeObj.species.name }
            // ability <array> 
            dbParams.Item.abilities = { SS: [] }
            for (elem in pokeObj.abilities) {
                dbParams.Item.abilities.SS[elem] = pokeObj.abilities[elem].ability.name
            }

            // gender <deprecate>
            dbParams.Item.gender = { S: 'unknown' }

            // height (decimeters)
            const deciMeterToFeet = (pokeObj.height / 3.048)
            const extractFeet = Math.floor(deciMeterToFeet)
            const extractInches = (deciMeterToFeet * 12) % 12

            dbParams.Item.height = { S: extractFeet + "\'" + addZero(extractInches.toFixed(0)) + "\"" }

            // number 
            dbParams.Item.number = { S: `${pokeObj.id}` }

            // picture
            dbParams.Item.picture = { S: pokeObj.sprites.front_default }

            // type <array>
            dbParams.Item.types = { SS: [] }
            for (elem in pokeObj.types) {
                dbParams.Item.types.SS[elem] = pokeObj.types[elem].type.name
            }

            // weakness <array>
            // Deprecated

            // weight
            const hectoGramToPounds = (pokeObj.weight / 4.536).toFixed(1);
            dbParams.Item.weight = { S: hectoGramToPounds + 'lbs' }

            return dynamodb.putItem(dbParams).promise().then((data) => {
                console.log('Populating pokemon: ' + i)
            }).catch((err) => {
                console.log(err)
            })
        });
}

(async function main() {
    const MAX_POKEMON = 151

    for (i = 1; i < MAX_POKEMON + 1; i++) {
        await populatePokemonN(i)
    }
})()

