public class VlcPlayerMinimal {

    public static void main(String[] args) {
        String vlcRcStatus = new VlcPlayerMinimal().getVlcRcStatus();
        System.out.println(vlcRcStatus);
    }

    public String getVlcRcStatus() {
        Client client = new Client();
        GetRequest getRequest = new GetRequest();
        String vlcRcStatus = executeGetRequest(client, getRequest);
        return vlcRcStatus;
    }

    private String executeGetRequest(Client client, GetRequest getRequest) {
        return client.execute(getRequest);
    }

    private class Client {
        public String execute(GetRequest getRequest) {
            return "{status: playing, id: 1}";
        }
    }
    private class GetRequest {
    }
}