## Custom Tools

1. Tools : Wrapper over a Python Function with Extra Arguments
1. Call our custom Tool

    ```python
    from langchain.agents import create_agent
    from langchain.tools import tool
    from langchain_core.messages import HumanMessage
    from langchain_openai import ChatOpenAI
    from dotenv import load_dotenv

    load_dotenv()

    @tool
    def search(query: str) -> str:
        """
        Tool that searches over the Internet
        Args:
            query : The query to search for
        Returns:
            The search result
        """
        print(f"Searching for {query}")
        return "The Weather is sunny in Tokyo"


    llm = ChatOpenAI(model="gpt-5-mini" )
    tools = [search]
    agent = create_agent(model=llm, tools=tools)


    def main():
        print("Hello from Langchain")
        result = agent.invoke(
            {"messages": HumanMessage(content="How is the weather in Tokyo?")}
        )
        # print(result)

        for message in result['messages']:
            print(f"Message Conent : {message.content}")

    if __name__ == "__main__":
        main()

    ```

    ```ps
    Hello from Langchain
    Searching for Tokyo current weather
    Message Conent : How is the weather in Tokyo?
    Message Conent : 
    Message Conent : The Weather is sunny in Tokyo
    Message Conent : Right now it’s sunny in Tokyo. Would you like more details (temperature, humidity, wind), an hourly forecast, or the outlook for the next few days?
    ```
1. Tool Call Argument in Langsmith 

    ```json
    {
    "input": "{'query': 'Tokyo current weather'}"
    }
    ```

## Custom Invocation of Tavily Search in a Custom Tool

1. Lets do the same operation using Tavily. [ PS: You need to define Tavily key in Environment Variables ]

    ```python
    from langchain.agents import create_agent
    from langchain.tools import tool
    from langchain_core.messages import HumanMessage
    from langchain_openai import ChatOpenAI
    from tavily import TavilyClient
    from dotenv import load_dotenv

    load_dotenv()

    tavily = TavilyClient()

    @tool
    def search(query: str) -> str:
        """
        Tool that searches over the Internet
        Args:
            query : The query to search for
        Returns:
            The search result
        """
        print(f"Searching for {query}")
        return tavily.search(query=query)


    llm = ChatOpenAI(model="gpt-5-mini" )
    tools = [search]
    agent = create_agent(model=llm, tools=tools)


    def main():
        print("Hello from Langchain")
        result = agent.invoke(
            {"messages": HumanMessage(content="How is the weather in Tokyo?")}
        )
        # print(result)

        for message in result['messages']:
            print(f"Message Conent : {message.content}")

    if __name__ == "__main__":
        main()
    ```


1. Output

    ```ps
    Hello from Langchain
    Searching for Tokyo current weather now
    Message Conent : How is the weather in Tokyo?
    Message Conent : 
    Message Conent : {"query": "Tokyo current weather now", "follow_up_questions": null, "answer": null, "images": [], "results": [{"title": "Weather in Tokyo, Japan", "url": "https://www.weatherapi.com/", "content": "{'location': {'name': 'Tokyo', 'region': 'Tokyo', 'country': 'Japan', 'lat': 35.6895, 'lon': 139.6917, 'tz_id': 'Asia/Tokyo', 'localtime_epoch': 1762754237, 'localtime': '2025-11-10 14:57'}, 'current': {'last_updated_epoch': 1762753500, 'last_updated': '2025-11-10 14:45', 'temp_c': 17.1, 'temp_f': 62.8, 'is_day': 1, 'condition': {'text': 'Light rain', 'icon': '//cdn.weatherapi.com/weather/64x64/day/296.png', 'code': 1183}, 'wind_mph': 20.6, 'wind_kph': 33.1, 'wind_degree': 336, 'wind_dir': 'NNW', 'pressure_mb': 1004.0, 'pressure_in': 29.65, 'precip_mm': 0.01, 'precip_in': 0.0, 'humidity': 55, 'cloud': 75, 'feelslike_c': 17.1, 'feelslike_f': 62.8, 'windchill_c': 20.3, 'windchill_f': 68.6, 'heatindex_c': 20.3, 'heatindex_f': 68.6, 'dewpoint_c': 2.7, 'dewpoint_f': 36.8, 'vis_km': 10.0, 'vis_miles': 6.0, 'uv': 1.9, 'gust_mph': 24.7, 'gust_kph': 39.8}}", "score": 0.992319, "raw_content": null}, {"url": "https://world-weather.info/forecast/japan/tokyo/november-2025/", "title": "Weather in Tokyo in November 2025 (Tōkyō-to)", "content": "Detailed ⚡ Tokyo Weather Forecast for November 2025 – day/night 🌡️ ... Monday, 10 November. Day. +64°. 18.1. 29.5. 48%. +57°. 06:11 am. 04:38 pm. Waning", "score": 0.9381752, "raw_content": null}, {"url": "https://www.weather25.com/asia/japan/tokyo?page=month&month=November", "title": "Tokyo weather in November 2025 - Weather25.com", "content": "The temperatures in Tokyo in November are usually low and can range between 51°F and 62°F. You can expect about 3 to 8 days of rain in Tokyo during the month", "score": 0.8800674, "raw_content": null}, {"url": "https://en.climate-data.org/asia/japan/tokyo/tokyo-3292/t/november-11/", "title": "Weather Tokyo in November 2025: Temperature & Climate", "content": "The average temperature in Tokyo during November is 12.1°C | 53.8°F. Water temperatures follow a steady trend: 20.9°C | 69.6°F in the first part, 20.1°C | 68.2°", "score": 0.8755427, "raw_content": null}, {"url": "https://www.easeweather.com/asia/japan/tokyo/november", "title": "Weather in Tokyo in November 2025 - Detailed Forecast", "content": "Enjoy moderate daytime temperatures of up to 17° and cooler nights around 12°. Ideal for a range of outdoor activities, from hiking to city tours.", "score": 0.8536326, "raw_content": null}], "response_time": 1.63, "request_id": "f2e713b2-e12c-449f-be36-7c2946e00007"}
    Message Conent : Right now in Tokyo (local time 2025-11-10 14:57):

    - Condition: Light rain  
    - Temperature: 17.1°C (62.8°F) — feels like 17°C  
    - Wind: NNW about 33 km/h (20.6 mph), gusts ~40 km/h (24.7 mph)  
    - Humidity: 55%  
    - Pressure: 1004 mb  
    - Visibility: ~10 km  
    - Cloud cover: ~75%  
    - Last update: 2025-11-10 14:45 (source: WeatherAPI)

    Want an hourly forecast or advice (umbrella, travel delays, etc.)?
    ```

1. Tool Call Argument in Langsmith 

    ```json
    {
    "input": "{'query': 'Tokyo current weather now'}"
    }
    ```

## Tavily Tool

1. Note - We are directly calling the Tavily Tool here.

    ```python
    from langchain.agents import create_agent
    from langchain.tools import tool
    from langchain_core.messages import HumanMessage
    from langchain_openai import ChatOpenAI
    from langchain_tavily import TavilySearch
    from dotenv import load_dotenv

    load_dotenv()


    llm = ChatOpenAI(model="gpt-5-mini" )
    tools = [TavilySearch()]
    agent = create_agent(model=llm, tools=tools)


    def main():
        print("Hello from Langchain")
        result = agent.invoke(
            {"messages": HumanMessage(content="How is the weather in Tokyo?")}
        )
        # print(result)

        for message in result['messages']:
            print(f"Message Conent : {message.content}")

    if __name__ == "__main__":
        main()

    ```

1. Output :

    ```ps
    Hello from Langchain
    Message Conent : How is the weather in Tokyo?
    Message Conent : 
    Message Conent : {"query": "Tokyo current weather temperature humidity wind forecast", "follow_up_questions": null, "answer": null, "images": [], "results": [{"title": "Weather in Tokyo", "url": "https://www.weatherapi.com/", "content": "{'location': {'name': 'Tokyo', 'region': 'Tokyo', 'country': 'Japan', 'lat': 35.6895, 'lon': 139.6917, 'tz_id': 'Asia/Tokyo', 'localtime_epoch': 1762754827, 'localtime': '2025-11-10 15:07'}, 'current': {'last_updated_epoch': 1762754400, 'last_updated': '2025-11-10 15:00', 'temp_c': 17.3, 'temp_f': 63.1, 'is_day': 1, 'condition': {'text': 'Light rain', 'icon': '//cdn.weatherapi.com/weather/64x64/day/296.png', 'code': 1183}, 'wind_mph': 24.2, 'wind_kph': 38.9, 'wind_degree': 347, 'wind_dir': 'NNW', 'pressure_mb': 1004.0, 'pressure_in': 29.65, 'precip_mm': 0.0, 'precip_in': 0.0, 'humidity': 55, 'cloud': 75, 'feelslike_c': 17.3, 'feelslike_f': 63.1, 'windchill_c': 19.2, 'windchill_f': 66.5, 'heatindex_c': 19.2, 'heatindex_f': 66.5, 'dewpoint_c': 1.9, 'dewpoint_f': 35.4, 'vis_km': 10.0, 'vis_miles': 6.0, 'uv': 0.8, 'gust_mph': 28.6, 'gust_kph': 46.0}}", "score": 0.945955, "raw_content": null}, {"url": "https://weathertrends360.com/Dashboard/Tokyo+Japan+1645:ST", "title": "Tokyo, Japan 14-day Weather Forecast - weathertrends360", "content": "Wind: NW @ 10 mph (G 21). Humidity: 37%. Dewpoint: 33°F. Precip Prob: 0 ... Monday 10 November 2025. Light Rain Showers. HIGH. LOW. 67°F. 49°F. Wind: NNW @ 15", "score": 0.939919, "raw_content": null}, {"url": "https://www.aqi.in/weather/japan/tokyo/tokyo", "title": "Tokyo Weather Conditions: Temperature | 30 Days Forecast - AQI.in", "content": "Humidity68 %. Feels13.4 °C. Cover100 %. Visibility10 km. Wind Speed & Dir.10.1 kmph / NE. UV0. Tokyo 30-Days (November 2025) Weather Forecast. See Hourly. Sun.", "score": 0.9257218, "raw_content": null}, {"url": "https://weather.metoffice.gov.uk/observations/xn76urzn2", "title": "Tokyo (Japan) last 24 hours weather - Met Office", "content": "Tokyo last 24 hours weather including temperature, wind, visibility, humidity and atmospheric pressure. ... Nov 2025. Observations table explained i · Daily", "score": 0.6737291, "raw_content": null}, {"url": "https://www.weatherapi.com/history/q/tokyo-3125553", "title": "Tokyo Weather History Tokyo, Japan - Weather API", "content": "Sat 08 Nov, 2025 Weather in Tokyo ; Temp, 15.3°c, 14.8°c ; Wind, 16.6 kmph, 18.7 kmph ; Precip, 0.00 mm, 0.00 mm ; Cloud, 100%, 67%", "score": 0.6350646, "raw_content": null}], "response_time": 1.58, "request_id": "0fa8506c-83ec-4b1c-ba97-21a9cef42064"}
    Message Conent : Right now in Tokyo (local time ~2025-11-10 15:00):

    - Condition: Light rain
    - Temperature: 17°C (63°F) — feels like 17°C
    - Humidity: 55%
    - Wind: NNW at about 39 km/h (24 mph), gusts up to ~46 km/h (29 mph)
    - Pressure: 1004 mb
    - Visibility: ~10 km
    - UV: low (0.8)

    If you want, I can give an hourly forecast, the outlook for tonight/tomorrow, or advice (e.g., carry an umbrella / expect breezy conditions). Which would you like?
    ```

1. Note : When look at Langsmith Trace - We see the LLM has added a few more arguments to the tool call in this case

    ```json
    {
    "query": "Tokyo current weather temperature humidity wind forecast",
    "search_depth": "basic",
    "time_range": "day",
    "topic": "general"
    }
    ```

1. Even though the answer is quite same but the agent was able to query better.

### 