using System.Text.Json;
using EVAL.Models;
using Microsoft.AspNetCore.Mvc;

namespace EVAL.Controllers;

public class CrmController : Controller
{
    
    private readonly HttpClient _httpClient;
    private readonly IHttpClientFactory _httpClientFactory;

    public CrmController(HttpClient httpClient,IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClient;
        _httpClientFactory = httpClientFactory;
    }
    [HttpPost("/update-seuil")]
    public async Task<IActionResult> update_seuil(string newSeuil)
    {
        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        using (var client = new HttpClient())
        {
            var apiUrl = $"http://localhost:8080/api/updateSeuil";

            var content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("newSeuil", newSeuil),
            });

            var response = await client.PostAsync(apiUrl, content);
        
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("ListeTotal"); // Rediriger vers la liste après suppression
            }
            else
            {
                var errorMessage = await response.Content.ReadAsStringAsync();
                return BadRequest("Erreur lors de la suppression : " + errorMessage);
            }
        }
    }
    
    //Form seuil
    [HttpGet("/form-seuil")]
    public async Task<IActionResult> ModifierSeuil(string ticketId)
    {

        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        return View();
    }
    
    //update ticket
    [HttpPost("/update-ticket")]
    public async Task<IActionResult> update_ticket(string ticketId,string newPrice)
    {
        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        using (var client = new HttpClient())
        {
            var apiUrl = $"http://localhost:8080/api/updateTicket";

            var content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("idTicket", ticketId),
                new KeyValuePair<string, string>("newPrice", newPrice)
            });

            var response = await client.PostAsync(apiUrl, content);
        
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("ListeTotal"); // Rediriger vers la liste après suppression
            }
            else
            {
                var errorMessage = await response.Content.ReadAsStringAsync();
                return BadRequest("Erreur lors de la suppression : " + errorMessage);
            }
        }
    }
    
    //update Lead
    [HttpPost("/update-lead")]
    public async Task<IActionResult> update_lead(string leadId,string newPrice)
    {
        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        using (var client = new HttpClient())
        {
            var apiUrl = $"http://localhost:8080/api/updateLead";

            var content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("idLead", leadId),
                new KeyValuePair<string, string>("newPrice", newPrice)
            });

            var response = await client.PostAsync(apiUrl, content);
        
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("ListeTotal"); // Rediriger vers la liste après suppression
            }
            else
            {
                var errorMessage = await response.Content.ReadAsStringAsync();
                return BadRequest("Erreur lors de la suppression : " + errorMessage);
            }
        }
    }

    //delete lead
    [HttpPost("/delete-lead")]
    public async Task<IActionResult> delete_lead(string leadId)
    {
        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        
        using (var client = new HttpClient())
        {
            var apiUrl = $"http://localhost:8080/api/deleteLead";

            var content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("idLead", leadId)
            });

            var response = await client.PostAsync(apiUrl, content);
        
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("ListeTotal"); // Rediriger vers la liste après suppression
            }
            else
            {
                var errorMessage = await response.Content.ReadAsStringAsync();
                return BadRequest("Erreur lors de la suppression : " + errorMessage);
            }
        }
    }
    
    //delete ticket
    [HttpPost("/delete-ticket")]
    public async Task<IActionResult> delete_ticket(string ticketId)
    {

        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        
        using (var client = new HttpClient())
        {
            var apiUrl = $"http://localhost:8080/api/deleteTicket";

            var content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("idTicket", ticketId)
            });

            var response = await client.PostAsync(apiUrl, content);
        
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("ListeTotal"); // Rediriger vers la liste après suppression
            }
            else
            {
                var errorMessage = await response.Content.ReadAsStringAsync();
                return BadRequest("Erreur lors de la suppression : " + errorMessage);
            }
        }
    }


    
    [HttpGet("/liste-total")]
    public async Task<IActionResult> ListeTotal()
    {
        string todayDate = DateTime.Now.ToString("yyyy-MM-ddTHH:mm:ss");
        
        //Check session
        bool jsessionid = await IsSessionValid(Request.Cookies["JSESSIONID"]);
        if (jsessionid == false)
        {
            return View("ErrorAuthorized");
        }
        //end of
        
        // URL Tickets avec le paramètre de date
        var apiUrlTicket = $"http://localhost:8080/api/getAllTicket?date={todayDate}"; 
        var responseTicket = await _httpClient.GetAsync(apiUrlTicket);
        
        //URL Leads  avec le paramètre de date
        var apiUrlLead = $"http://localhost:8080/api/getAllLead?date={todayDate}"; 
        var responseLead = await _httpClient.GetAsync(apiUrlLead);
        
        //URL Budget  avec le paramètre de date
        var apiUrlBudget = $"http://localhost:8080/api/getAllBudget?date={todayDate}"; 
        var responseBudget = await _httpClient.GetAsync(apiUrlBudget);
        
        List<Ticket> tickets = new List<Ticket>();
        List<Lead> leads = new List<Lead>();
        List<Budget> budgets = new List<Budget>();
        if (responseTicket.IsSuccessStatusCode && responseLead.IsSuccessStatusCode && responseBudget.IsSuccessStatusCode)
        {
            //data ticket
            var resultTicket = await responseTicket.Content.ReadAsStringAsync();
            tickets = JsonSerializer.Deserialize<List<Ticket>>(resultTicket);
            
            //data lead
            var resultLead = await responseLead.Content.ReadAsStringAsync();
            leads = JsonSerializer.Deserialize<List<Lead>>(resultLead);
            
            //data budget
            var resultBudget = await responseBudget.Content.ReadAsStringAsync();
            budgets = JsonSerializer.Deserialize<List<Budget>>(resultBudget);
            
        }
        ViewData["ticketList"] = tickets;
        ViewData["leadList"] = leads;
        ViewData["budgetList"] = budgets;
        ViewData["fourFirst"] = Budget.GetFourFirst(budgets);
        
        ViewData["ticketSomme"] = Ticket.SumTicket(tickets);
        ViewData["leadSomme"] = Lead.SumLead(leads);
        ViewData["budgetSomme"] = Budget.SumBudgets(budgets);
        
        return View();
    }
    
    
    //fonction pour check la session
    private async Task<bool> IsSessionValid(string jsessionId)
    {
        if (string.IsNullOrEmpty(jsessionId))
        {
            return false;
        }
        try
        {
            var requestMessage = new HttpRequestMessage(HttpMethod.Get, $"http://localhost:8080/api/checkSession?JSessionID={jsessionId}");
            requestMessage.Headers.Add("Cookie", $"JSESSIONID={jsessionId}");
            var response = await _httpClient.SendAsync(requestMessage);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                return content.Equals("true", StringComparison.OrdinalIgnoreCase);
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Erreur lors de la vérification de la session : {ex.Message}");
        }

        return false;
    }

}