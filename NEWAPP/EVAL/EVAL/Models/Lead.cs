using System.Text.Json.Serialization;

namespace EVAL.Models;

public class Lead
{
    [JsonPropertyName("leadId")]
    public int LeadId { get; set; }

    [JsonPropertyName("name")]
    public string Name { get; set; }

    [JsonPropertyName("status")]
    public string Status { get; set; }

    [JsonPropertyName("phone")]
    public string Phone { get; set; }

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
    
    public static double SumLead(List<Lead> leads)
    {
        return leads.Sum(b => b.AmountDepense);
    }
} 