var AWS = require("aws-sdk");
var docClient = new AWS.DynamoDB.DocumentClient();

function queryTable(params, callback) {

    return docClient.query(params).promise().then((data) => {
        callback(null, {
            "statusCode": 200,
            "headers": {
                "my_header": "my_value"
            },
            "body": JSON.stringify(data.Items[0]),
            "isBase64Encoded": false
        });


    }).catch((err) => {
        console.log(err)
    })
}
exports.handler = async (event, context, callback) => {

    console.log("Querying for movies from 1985.");

    var params = {
        TableName: "pokeapi",
        KeyConditionExpression: "#yr = :yyyy",
        ExpressionAttributeNames: {
            "#yr": "name"
        },
        ExpressionAttributeValues: {
            ":yyyy": event.pathParameters.pokemon
        }
    };

    return queryTable(params, callback)
};
