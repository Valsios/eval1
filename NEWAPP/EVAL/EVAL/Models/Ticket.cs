namespace EVAL.Models;

using System.Text.Json.Serialization;

public class Ticket
{
    [JsonPropertyName("ticketId")]
    public int TicketId { get; set; }

    [JsonPropertyName("subject")]
    public string Subject { get; set; }

    [JsonPropertyName("description")]
    public string Description { get; set; }

    [JsonPropertyName("status")]
    public string Status { get; set; }

    [JsonPropertyName("priority")]
    public string Priority { get; set; }

    [JsonPropertyName("nom_manager")]
    public string NomManager { get; set; }

    [JsonPropertyName("nom_employee")]
    public string NomEmployee { get; set; }

    [JsonPropertyName("id_customer")]
    public int CustomerId { get; set; }
    
    [JsonPropertyName("nom_customer")]
    public string NomCustomer { get; set; }

    [JsonPropertyName("createdAt")]
    public long CreatedAt { get; set; }

    [JsonPropertyName("amount_depense")]
    public double AmountDepense { get; set; }

    [JsonPropertyName("description_depense")]
    public string DescriptionDepense { get; set; }
    
    public static double SumTicket(List<Ticket> tickets)
    {
        return tickets.Sum(b => b.AmountDepense);
    }
}
