namespace EVAL.Controllers.security;

public class SessionMiddleware
{
    private readonly RequestDelegate _next;
    private readonly IHttpClientFactory _httpClientFactory;

    public SessionMiddleware(RequestDelegate next, IHttpClientFactory httpClientFactory)
    {
        _next = next;
        _httpClientFactory = httpClientFactory;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        var jsessionId = context.Request.Cookies["JSESSIONID"];
        if (string.IsNullOrEmpty(jsessionId) || !await IsSessionValid(jsessionId))
        {
            context.Response.StatusCode = StatusCodes.Status401Unauthorized;
            return;
        }

        await _next(context);
    }

    private async Task<bool> IsSessionValid(string jsessionId)
    {
        var client = _httpClientFactory.CreateClient();
        client.DefaultRequestHeaders.Add("Cookie", $"JSESSIONID={jsessionId}");

        var response = await client.GetAsync( $"http://localhost:8080/api/checkSession");
        return response.IsSuccessStatusCode && await response.Content.ReadAsStringAsync() == "true";
    }
}
