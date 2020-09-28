import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.ScriptObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.JSONTokener;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Test {


    public static void main(String[] args) {
        /*String schema = "{\n" +
                "   \"asyncapi\": \"2.0.0\",\n" +
                "   \"info\": {\n" +
                "      \"title\": \"Streetlights API\",\n" +
                "      \"version\": \"1.0.0\",\n" +
                "      \"description\": \"The Smartylighting Streetlights API allows you to remotely manage the city lights.\\n### Check out its awesome features:\\n* Turn a specific streetlight on/off \uD83C\uDF03\\n* Dim a specific streetlight \uD83D\uDE0E\\n* Receive real-time information about environmental lighting conditions \uD83D\uDCC8\\n\",\n" +
                "      \"license\": {\n" +
                "         \"name\": \"Apache 2.0\",\n" +
                "         \"url\": \"https://www.apache.org/licenses/LICENSE-2.0\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"servers\": {\n" +
                "      \"production\": {\n" +
                "         \"url\": \"test.mosquitto.org:{port}\",\n" +
                "         \"protocol\": \"mqtt\",\n" +
                "         \"description\": \"Test broker\",\n" +
                "         \"variables\": {\n" +
                "            \"port\": {\n" +
                "               \"description\": \"Secure connection (TLS) is available through port 8883.\",\n" +
                "               \"default\": \"1883\",\n" +
                "               \"enum\": [\n" +
                "                  \"1883\",\n" +
                "                  \"8883\"\n" +
                "               ]\n" +
                "            }\n" +
                "         },\n" +
                "         \"security\": [\n" +
                "            {\n" +
                "               \"apiKey\": []\n" +
                "            },\n" +
                "            {\n" +
                "               \"supportedOauthFlows\": [\n" +
                "                  \"streetlights:on\",\n" +
                "                  \"streetlights:off\",\n" +
                "                  \"streetlights:dim\"\n" +
                "               ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"openIdConnectWellKnown\": []\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   },\n" +
                "   \"defaultContentType\": \"application/json\",\n" +
                "   \"channels\": {\n" +
                "      \"smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured\": {\n" +
                "         \"description\": \"The topic on which measured values may be produced and consumed.\",\n" +
                "         \"parameters\": {\n" +
                "            \"streetlightId\": {\n" +
                "               \"$ref\": \"#/components/parameters/streetlightId\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"publish\": {\n" +
                "            \"summary\": \"Inform about environmental lighting conditions of a particular streetlight.\",\n" +
                "            \"operationId\": \"receiveLightMeasurement\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/operationTraits/kafka\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"message\": {\n" +
                "               \"$ref\": \"#/components/messages/lightMeasured\"\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"smartylighting/streetlights/1/0/action/{streetlightId}/turn/on\": {\n" +
                "         \"parameters\": {\n" +
                "            \"streetlightId\": {\n" +
                "               \"$ref\": \"#/components/parameters/streetlightId\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"subscribe\": {\n" +
                "            \"operationId\": \"turnOn\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/operationTraits/kafka\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"message\": {\n" +
                "               \"$ref\": \"#/components/messages/turnOnOff\"\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"smartylighting/streetlights/1/0/action/{streetlightId}/turn/off\": {\n" +
                "         \"parameters\": {\n" +
                "            \"streetlightId\": {\n" +
                "               \"$ref\": \"#/components/parameters/streetlightId\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"subscribe\": {\n" +
                "            \"operationId\": \"turnOff\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/operationTraits/kafka\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"message\": {\n" +
                "               \"$ref\": \"#/components/messages/turnOnOff\"\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"smartylighting/streetlights/1/0/action/{streetlightId}/dim\": {\n" +
                "         \"parameters\": {\n" +
                "            \"streetlightId\": {\n" +
                "               \"$ref\": \"#/components/parameters/streetlightId\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"subscribe\": {\n" +
                "            \"operationId\": \"dimLight\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/operationTraits/kafka\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"message\": {\n" +
                "               \"$ref\": \"#/components/messages/dimLight\"\n" +
                "            }\n" +
                "         }\n" +
                "      }\n" +
                "   },\n" +
                "   \"components\": {\n" +
                "      \"messages\": {\n" +
                "         \"lightMeasured\": {\n" +
                "            \"name\": \"lightMeasured\",\n" +
                "            \"title\": \"Light measured\",\n" +
                "            \"summary\": \"Inform about environmental lighting conditions of a particular streetlight.\",\n" +
                "            \"contentType\": \"application/json\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/messageTraits/commonHeaders\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"payload\": {\n" +
                "               \"$ref\": \"#/components/schemas/lightMeasuredPayload\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"turnOnOff\": {\n" +
                "            \"name\": \"turnOnOff\",\n" +
                "            \"title\": \"Turn on/off\",\n" +
                "            \"summary\": \"Command a particular streetlight to turn the lights on or off.\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/messageTraits/commonHeaders\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"payload\": {\n" +
                "               \"$ref\": \"#/components/schemas/turnOnOffPayload\"\n" +
                "            }\n" +
                "         },\n" +
                "         \"dimLight\": {\n" +
                "            \"name\": \"dimLight\",\n" +
                "            \"title\": \"Dim light\",\n" +
                "            \"summary\": \"Command a particular streetlight to dim the lights.\",\n" +
                "            \"traits\": [\n" +
                "               {\n" +
                "                  \"$ref\": \"#/components/messageTraits/commonHeaders\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"payload\": {\n" +
                "               \"$ref\": \"#/components/schemas/dimLightPayload\"\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"schemas\": {\n" +
                "         \"lightMeasuredPayload\": {\n" +
                "            \"type\": \"object\",\n" +
                "            \"properties\": {\n" +
                "               \"lumens\": {\n" +
                "                  \"type\": \"integer\",\n" +
                "                  \"minimum\": 0,\n" +
                "                  \"description\": \"Light intensity measured in lumens.\"\n" +
                "               },\n" +
                "               \"sentAt\": {\n" +
                "                  \"$ref\": \"#/components/schemas/sentAt\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"turnOnOffPayload\": {\n" +
                "            \"type\": \"object\",\n" +
                "            \"properties\": {\n" +
                "               \"command\": {\n" +
                "                  \"type\": \"string\",\n" +
                "                  \"enum\": [\n" +
                "                     \"on\",\n" +
                "                     \"off\"\n" +
                "                  ],\n" +
                "                  \"description\": \"Whether to turn on or off the light.\"\n" +
                "               },\n" +
                "               \"sentAt\": {\n" +
                "                  \"$ref\": \"#/components/schemas/sentAt\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"dimLightPayload\": {\n" +
                "            \"type\": \"object\",\n" +
                "            \"properties\": {\n" +
                "               \"percentage\": {\n" +
                "                  \"type\": \"integer\",\n" +
                "                  \"description\": \"Percentage to which the light should be dimmed to.\",\n" +
                "                  \"minimum\": 0,\n" +
                "                  \"maximum\": 100\n" +
                "               },\n" +
                "               \"sentAt\": {\n" +
                "                  \"$ref\": \"#/components/schemas/sentAt\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"sentAt\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"format\": \"date-time\",\n" +
                "            \"description\": \"Date and time when the message was sent.\"\n" +
                "         }\n" +
                "      },\n" +
                "      \"securitySchemes\": {\n" +
                "         \"apiKey\": {\n" +
                "            \"type\": \"apiKey\",\n" +
                "            \"in\": \"user\",\n" +
                "            \"description\": \"Provide your API key as the user and leave the password empty.\"\n" +
                "         },\n" +
                "         \"supportedOauthFlows\": {\n" +
                "            \"type\": \"oauth2\",\n" +
                "            \"description\": \"Flows to support OAuth 2.0\",\n" +
                "            \"flows\": {\n" +
                "               \"implicit\": {\n" +
                "                  \"authorizationUrl\": \"https://authserver.example/auth\",\n" +
                "                  \"scopes\": {\n" +
                "                     \"streetlights:on\": \"Ability to switch lights on\",\n" +
                "                     \"streetlights:off\": \"Ability to switch lights off\",\n" +
                "                     \"streetlights:dim\": \"Ability to dim the lights\"\n" +
                "                  }\n" +
                "               },\n" +
                "               \"password\": {\n" +
                "                  \"tokenUrl\": \"https://authserver.example/token\",\n" +
                "                  \"scopes\": {\n" +
                "                     \"streetlights:on\": \"Ability to switch lights on\",\n" +
                "                     \"streetlights:off\": \"Ability to switch lights off\",\n" +
                "                     \"streetlights:dim\": \"Ability to dim the lights\"\n" +
                "                  }\n" +
                "               },\n" +
                "               \"clientCredentials\": {\n" +
                "                  \"tokenUrl\": \"https://authserver.example/token\",\n" +
                "                  \"scopes\": {\n" +
                "                     \"streetlights:on\": \"Ability to switch lights on\",\n" +
                "                     \"streetlights:off\": \"Ability to switch lights off\",\n" +
                "                     \"streetlights:dim\": \"Ability to dim the lights\"\n" +
                "                  }\n" +
                "               },\n" +
                "               \"authorizationCode\": {\n" +
                "                  \"authorizationUrl\": \"https://authserver.example/auth\",\n" +
                "                  \"tokenUrl\": \"https://authserver.example/token\",\n" +
                "                  \"refreshUrl\": \"https://authserver.example/refresh\",\n" +
                "                  \"scopes\": {\n" +
                "                     \"streetlights:on\": \"Ability to switch lights on\",\n" +
                "                     \"streetlights:off\": \"Ability to switch lights off\",\n" +
                "                     \"streetlights:dim\": \"Ability to dim the lights\"\n" +
                "                  }\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"openIdConnectWellKnown\": {\n" +
                "            \"type\": \"openIdConnect\",\n" +
                "            \"openIdConnectUrl\": \"https://authserver.example/.well-known\"\n" +
                "         }\n" +
                "      },\n" +
                "      \"parameters\": {\n" +
                "         \"streetlightId\": {\n" +
                "            \"description\": \"The ID of the streetlight.\",\n" +
                "            \"schema\": {\n" +
                "               \"type\": \"string\"\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"messageTraits\": {\n" +
                "         \"commonHeaders\": {\n" +
                "            \"headers\": {\n" +
                "               \"type\": \"object\",\n" +
                "               \"properties\": {\n" +
                "                  \"my-app-header\": {\n" +
                "                     \"type\": \"integer\",\n" +
                "                     \"minimum\": 0,\n" +
                "                     \"maximum\": 100\n" +
                "                  }\n" +
                "               }\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      \"operationTraits\": {\n" +
                "         \"kafka\": {\n" +
                "            \"bindings\": {\n" +
                "               \"kafka\": {\n" +
                "                  \"clientId\": \"my-app-id\"\n" +
                "               }\n" +
                "            }\n" +
                "         }\n" +
                "      }\n" +
                "   }\n" +
                "}";*/
        //AsyncAPI asyncAPI = new AsyncAPI(schema);
        /*System.out.println("AsyncAPI version: " + asyncAPI.getAsyncApiVersion());
        System.out.println("ID: " + asyncAPI.getId());
        System.out.println(asyncAPI.getInfo().getTitle());
        System.out.println(asyncAPI.getInfo().getVersion());
        System.out.println(asyncAPI.getInfo().getDescription());
        System.out.println(asyncAPI.getInfo().getTermsOfService());
        if (asyncAPI.getInfo().getContact() != null){
            System.out.println(asyncAPI.getInfo().getContact().getName());
            System.out.println(asyncAPI.getInfo().getContact().getUrl());
            System.out.println(asyncAPI.getInfo().getContact().getEmail());
        }
        if (asyncAPI.getInfo().getLicense() != null){
            System.out.println(asyncAPI.getInfo().getLicense().getName());
            System.out.println(asyncAPI.getInfo().getLicense().getUrl());
        }*/
        //System.out.println(asyncAPI.getServers());
        /*String a = new JSONObject(schema).getJSONObject("servers").getJSONObject("production").getJSONArray("security").get(1).toString();
        JSONObject x = AsyncAPI.objectToJSONObject(new JSONObject(schema).getJSONObject("servers").getJSONObject("production").getJSONArray("security").get(1));
        System.out.println(x);
        for (Iterator<String> it = x.keys(); it.hasNext(); ) {
            String key = it.next();
            System.out.println(key);
        }*/
        /*ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(schema);
            for (Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ) {
                String x = String.valueOf(it.next());
                System.out.println(x);
            }
            JsonNode path = jsonNode.findPath("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured").get("publish").get("traits").get(0).get("$ref");
            System.out.println(path);
            String actualPath = path.asText().substring(1);
            JsonPointer jsonPointer = JsonPointer.compile(actualPath);
            System.out.println(jsonNode.at(jsonPointer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        ObjectMapper objectMapper = new ObjectMapper();
    }
}
