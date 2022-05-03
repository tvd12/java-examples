package com.tvd12.example.concurrent.delegate;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

interface ContractService {

    void addContract(Contract contract);

}

interface ContractDelegate {

    void onContractAdded(Contract contract);

}

interface RequestHistoryService {

    void save(RequestHistory history);

}

@Setter
public class ContractServiceImpl implements ContractService {

    private AdminContractDelegate contractDelegate;

    public static void main(String[] args) {
        AdminContractDelegate contractDelegate = new AdminContractDelegate();
        contractDelegate.setRequestHistoryService(new RequestHistoryServiceMock());
        ContractServiceImpl contractService = new ContractServiceImpl();
        contractService.setContractDelegate(contractDelegate);
        contractService.addContract(new Contract("dungtv"));
    }

    @Override
    public void addContract(Contract contract) {
        //repo.save(contract);
        if (contractDelegate != null) {
            contractDelegate.onContractAdded(contract);
        }
    }

}

@Data
@ToString
@AllArgsConstructor
class Contract {

    private String name;

}

@Data
@ToString
class RequestHistory {

    private static final AtomicInteger ID_GENTOR = new AtomicInteger();
    private long id = ID_GENTOR.incrementAndGet();
    private String oldData;
    private String newData;
    private String link;

}

class RequestHistoryServiceMock implements RequestHistoryService {
    @Override
    public void save(RequestHistory history) {
        System.out.println("add new history: " + history);
    }
}

@Setter
class AdminContractDelegate implements ContractDelegate {

    private RequestHistoryService requestHistoryService;

    @Override
    public void onContractAdded(Contract contract) {
        RequestHistory history = new RequestHistory();
        history.setOldData(null);
        history.setNewData(contract.toString());
        history.setLink("https://service.viettel.com/hop-dong/3");
        requestHistoryService.save(history);
    }

}
