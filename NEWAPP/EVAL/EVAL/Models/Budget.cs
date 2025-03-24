using System.Text.Json.Serialization;

namespace EVAL.Models;

public class Budget
{
    [JsonPropertyName("idBudget")]
    public int IdBudget { get; set; }

    [JsonPropertyName("id_customer")]
    public int CustomerId { get; set; }
    
    [JsonPropertyName("nom_customer")]
    public string NomCustomer { get; set; }

    [JsonPropertyName("dateBudget")]
    public long DateBudget { get; set; }

    [JsonPropertyName("amount")]
    public double Amount { get; set; }
    
    public static Dictionary<(string, int), double> GetFourFirst(List<Budget> list)
    {
        Dictionary<(string, int), double> toStock = new Dictionary<(string, int), double>();

        foreach (var budget in list)
        {
            var key = (budget.NomCustomer, budget.CustomerId);

            if (toStock.ContainsKey(key))
            {
                toStock[key] += budget.Amount;
            }
            else
            {
                toStock[key] = budget.Amount;
            }
        }
        return toStock.OrderByDescending(x => x.Value)
            .Take(4)
            .ToDictionary(k => k.Key, v => v.Value);
    }
    public static double SumBudgets(List<Budget> budgets)
    {
        return budgets.Sum(b => b.Amount);
    }
}