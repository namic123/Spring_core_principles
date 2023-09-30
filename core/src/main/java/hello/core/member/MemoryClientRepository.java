package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryClientRepository implements ClientRepository {
    private static Map<Long, Client> store = new HashMap<>();
    @Override
    public void save(Client client) {
        store.put(client.getId(), client);
    }

    @Override
    public Client findById(Long memberId) {
        return store.get(memberId);
    }
}
