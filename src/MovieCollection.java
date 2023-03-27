import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.println("Enter a cast member: ");
        String searchTerm=scanner.nextLine().toLowerCase();
        ArrayList<String> cast=new ArrayList<String>();
        for(int i=0; i<movies.size(); i++){
            String currentCast=movies.get(i).getCast()+"|";
            for(int j=0; j<currentCast.length(); j++){
                String currentMember=currentCast.substring(0,currentCast.indexOf("|"));
                if(!cast.contains(currentMember)){
                    cast.add(currentMember);
                }
                currentCast=currentCast.substring(currentCast.indexOf("|")+1);
            }
        }
        stringSort(cast);
        for(int i=0; i<cast.size(); i++){
            if(!cast.get(i).toLowerCase().contains(searchTerm)){
                cast.remove(i);
                i--;
            }
        }
        for(int i=0; i<cast.size(); i++){
            System.out.println((i+1)+". "+cast.get(i));
        }
        System.out.println("Which cast member would you like to learn more about?");
        System.out.println("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Movie> titles =new ArrayList<Movie>();
        for(int i=0; i<movies.size(); i++){
            if(movies.get(i).getCast().contains(cast.get(choice-1))){
                titles.add(movies.get(i));
            }
        }
        sortResults(titles);
        for(int i=0; i<titles.size(); i++){
            System.out.println((i+1)+". "+titles.get(i).getTitle());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice1 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = titles.get(choice1 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }
    private void stringSort(ArrayList<String> a){
        for(int i=0; i<a.size(); i++){
            int min=i;
            String current=a.get(min);
            for(int j=i+1; j<a.size(); j++){
                if(a.get(j).compareTo(a.get(min))<0){
                    min=j;
                }
            }
            String temp=a.get(min);
            a.set(min, current);
            a.set(i, temp);
        }
    }

    private void searchKeywords()
    { System.out.print("Enter a keyword: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keywords = movies.get(i).getKeywords();
            keywords=keywords.toLowerCase();

            if (keywords.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String>genres=new ArrayList<>();
        for(int i=0; i<movies.size(); i++){
            String currentGenres=movies.get(i).getGenres()+"|";
            for(int j=0; j<currentGenres.length(); j++){
                String currentGenre=currentGenres.substring(0,currentGenres.indexOf("|"));
                if(!genres.contains(currentGenre)){
                    genres.add(currentGenre);
                }
                currentGenres=currentGenres.substring(currentGenres.indexOf("|")+1);
            }
        }
        stringSort(genres);
        for(int i=0; i<genres.size(); i++){
            System.out.println((i+1)+". "+genres.get(i));
        }
        System.out.println("What genre do you want to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedGenre = genres.get(choice - 1);

        ArrayList<Movie>titles=new ArrayList<>();
        for(int i=0; i<movies.size(); i++){
            if(movies.get(i).getGenres().contains(selectedGenre)){
                titles.add(movies.get(i));
            }
        }
        sortResults(titles);
        for(int i=0; i<titles.size(); i++){
            System.out.println((i+1)+". "+titles.get(i).getTitle());
        }
        System.out.println("What movie do you want to learn more about?");
        System.out.print("Enter number: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = titles.get(choice - 1);
        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        ArrayList<Movie>titles=movies;
        for(int i=0; i<50; i++){
            int pos=i;
            double maxRating=titles.get(pos).getUserRating();
            for(int j=i+1; j<titles.size(); j++){
                if(titles.get(j).getUserRating()>titles.get(pos).getUserRating()){
                    pos=j;
                }
                Movie temp=titles.get(i);
                titles.set(i, titles.get(pos));
                titles.set(pos, temp);
            }
            System.out.println(i+1+". "+titles.get(i).getTitle()+" "+ titles.get(i).getUserRating());
        }
        System.out.println("What movie do you want to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = titles.get(choice - 1);
        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        ArrayList<Movie>titles=movies;
        for(int i=0; i<50; i++){
            int pos=i;
            double maxRevenue=titles.get(pos).getRevenue();
            for(int j=i+1; j<titles.size(); j++){
                if(titles.get(j).getRevenue()>titles.get(pos).getRevenue()){
                    pos=j;
                }
                Movie temp=titles.get(i);
                titles.set(i, titles.get(pos));
                titles.set(pos, temp);
            }
            System.out.println(i+1+". "+titles.get(i).getTitle()+" "+ titles.get(i).getRevenue());
        }
        System.out.println("What movie do you want to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = titles.get(choice - 1);
        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}