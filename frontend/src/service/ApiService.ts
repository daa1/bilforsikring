import { BilforsikringData } from "../model/BilforsikringData";

export default class ApiService {
  sendBestilling(bestilling: BilforsikringData): Promise<Response> {
    console.log(bestilling);
    return fetch(`/gateway/api/v1/bilforsikring`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bestilling),
    });
  }

  getBestilling(bestillingId: string): Promise<Response> {
    return fetch(`/gateway/api/v1/bilforsikring/${bestillingId}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}
