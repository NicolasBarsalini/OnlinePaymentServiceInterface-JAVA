package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;
import model.interfaces.OnlinePaymentService;

public class ContractService {

	private OnlinePaymentService onlinePaymentService; //contract service depende de um serviço de pagamento
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months){
		
		double basicQuota = contract.getTotalValue() / months; //parcela básica
		
		for(int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);//vencimento da parcela
			
			double interest = onlinePaymentService.interest(basicQuota, i); //juros
			double fee = onlinePaymentService.paymentFee(basicQuota + interest); //taxa
			double quota = basicQuota + interest + fee;
			
			contract.getInstallments().add(new Installment(dueDate, quota));
		}
	
	}
	
}
