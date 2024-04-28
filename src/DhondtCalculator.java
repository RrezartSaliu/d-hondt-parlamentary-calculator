import java.util.*;

public class DhondtCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfSeatsPerZone = 20;
        System.out.print("Enter number of parties: ");
        int partiesNum = scanner.nextInt();

        List<Party> partyList = new ArrayList<>();
        for(int i=0; i<partiesNum; i++){
            System.out.print("Enter party name: ");
            String name = scanner.next();
            System.out.print("Enter number of votes: ");
            int votes = scanner.nextInt();
            partyList.add(new Party(name, votes));
        }

        int seats_calculated = 0;
        while (seats_calculated != numberOfSeatsPerZone){
            double quotient = 0.0;
            Party biggestQuotentParty = null;

            for (Party party: partyList){
                if(party.getQuotient() > quotient){
                    quotient = party.getQuotient();
                    biggestQuotentParty = party;
                }
            }

            Party finalBiggestQuotentParty = biggestQuotentParty;
            partyList.stream().filter(party -> party.getName().equals(finalBiggestQuotentParty.getName())).findFirst().orElseThrow().addSeat();

            seats_calculated++;
        }

        partyList.stream().sorted(Comparator.comparing(Party::getVotes).reversed()).forEach(System.out::println);
    }
}

class Party {
    private String name;
    private int seats;
    private int votes;

    public Party(String name, int votes) {
        this.seats = 0;
        this.votes = votes;
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public String getName (){
        return name;
    }

    public void addSeat(){
        seats++;
    }

    public double getQuotient(){
        return votes/(seats+1.0);
    }

    @Override
    public String toString() {
        return "Party{" +
                "name='" + name + '\'' +
                ", seats=" + seats +
                ", votes=" + votes +
                '}';
    }
}