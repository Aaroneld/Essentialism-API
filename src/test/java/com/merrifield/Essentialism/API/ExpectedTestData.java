package com.merrifield.Essentialism.API;

import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.Value;

import java.util.List;
import java.util.Set;

public interface ExpectedTestData {

    User FUNCTIONAL_TEST_USER = new User(12,"test", "test", "John", "Doe", "email@email.com", "818-111-2121");

    List<User> TEST_USERS = List.of(
            new User(1,"enorth0", "aw1Ntjfptb", "Elnar", "North", "enorth0@cam.ac.uk", "762-183-2223"),
            new User(2,"mpesselt1", "3S4rGplKWgB", "Maighdiln", "Pesselt", "mpesselt1@slideshare.net", "256-452-6591"),
            new User(3,"csuller2", "VP1ZyDjOT", "Cesya", "Suller", "csuller2@free.fr", "100-766-3266"),
            new User(4,"cnuccii3", "HiccEpL1c", "Cindy", "Nuccii", "cnuccii3@harvard.edu", "222-852-4140"),
            new User(5,"aguppy4", "E8gCJMf", "Ardelia", "Guppy", "aguppy4@state.tx.us", "685-177-7409"),
            new User(6,"tyankeev5", "BnogDETe", "Tadeas", "Yankeev", "tyankeev5@reverbnation.com", "409-944-7693"),
            new User(7,"rboyan6", "jEK9jBaG2w", "Raff", "Boyan", "rboyan6@exblog.jp", "744-422-2733"),
            new User(8,"dredwood7", "r5cHJBHNxCM9", "Dara", "Redwood", "dredwood7@whitehouse.gov", "531-402-7908"),
            new User(9,"bricarde8", "DbWcPFWDEb", "Balduin", "Ricarde", "bricarde8@tiny.cc", "994-450-3516"),
            new User(10,"lsemechik9", "SBDvdYQK", "Leontyne", "Semechik", "lsemechik9@telegraph.co.uk", "647-410-7274"),
            new User(11,"aaroneld", "admin", "Aaron", "Merrifield", "merrifield48@gmail.com", "518-203-0910"),
            new User(12,"test", "test", "John", "Doe", "email@email.com", "818-111-2121")
    );
    
    List<Project> TEST_PROJECTS = List.of(
            new Project ("Rohan-Lowe", "Phasellus in felis. Donec semper sapien a libero."),
            new Project ("Boehm-O'Hara and Schimmel", "Nunc rhoncus dui vel sem. Sed sagittis."),
            new Project ("Franecki-O'Reilly", "Suspendisse accumsan tortor quis turpis. Sed ante."),
            new Project ("Keebler, Hartmann and Jerde", "Mauris sit amet eros. Suspendisse accumsan tortor quis turpis."),
            new Project ("Howell-Corwin", "Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum."),
            new Project ("Auer-Glover", "Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl."),
            new Project ("Reichel Group", "Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio."),
            new Project ("Heller, Weber and Bartell", "Aliquam erat volutpat. In congue."),
            new Project ("Brekke Inc", "Proin eu mi. Nulla ac enim."),
            new Project ("Swift-Mayer", "Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst."),
            new Project ("Hirthe, Beahan and Treutel", "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien."),
            new Project ("McGlynn-Nader", "Vestibulum ante  t. Donec odio justo sapien urna pretium nisl, ut volutpat sapien arcu sed augue."),
            new Project ("Ankunding-Wolff", "Morbi quis tortor uet. Maecenas leo odio, condimentum id,  justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui."),
            new Project ("Streich, Hills and Paucek", "Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor."),
            new Project ("Hauck LLC", "Vestibulum ante magna vestibulum  non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.")
            
    );

    Set<Value> TEST_VALUES = Set.of (
            new Value("Acceptance Accomplishment"),
            new Value("Accountability"),
            new Value("Accuracy"),
            new Value("Achievement"),
            new Value("Adaptability"),
            new Value("Alertness"),
            new Value("Altruism"),
            new Value("Ambition"),
            new Value("Amusement"),
            new Value("Assertiveness"),
            new Value("Attentive"),
            new Value("Awareness"),
            new Value("Balance"),
            new Value("Beauty"),
            new Value("Boldness"),
            new Value("Bravery"),
            new Value("Brilliance"),
            new Value("Calm"),
            new Value("Candor"),
            new Value("Capable"),
            new Value("Careful"),
            new Value("Certainty"),
            new Value("Challenge"),
            new Value("Charity"),
            new Value("Cleanliness"),
            new Value("Clear"),
            new Value("Clever"),
            new Value("Comfort"),
            new Value("Commitment"),
            new Value("Common"),
            new Value("sense Communication"),
            new Value("Community"),
            new Value("Compassion"),
            new Value("Competence"),
            new Value("Concentration"),
            new Value("Confidence"),
            new Value("Connection"),
            new Value("Consciousness"),
            new Value("Consistency"),
            new Value("Contentment"),
            new Value("Contribution"),
            new Value("Control"),
            new Value("Conviction"),
            new Value("Cooperation"),
            new Value("Courage"),
            new Value("Courtesy"),
            new Value("Creation"),
            new Value("Creativity"),
            new Value("Credibility"),
            new Value("Curiosity"),
            new Value("Decisive"),
            new Value("Decisiveness"),
            new Value("Dedication"),
            new Value("Dependability"),
            new Value("Determination"),
            new Value("Development"),
            new Value("Devotion"),
            new Value("Dignity"),
            new Value("Discipline"),
            new Value("Discovery"),
            new Value("Drive"),
            new Value("Effectiveness"),
            new Value("Efficiency"),
            new Value("Empathy"),
            new Value("Empower"),
            new Value("Endurance"),
            new Value("Energy"),
            new Value("Enjoyment"),
            new Value("Enthusiasm"),
            new Value("Equality"),
            new Value("Ethical"),
            new Value("Excellence"),
            new Value("Experience"),
            new Value("Exploration"),
            new Value("Expressive"),
            new Value("Fairness"),
            new Value("Family"),
            new Value("Famous"),
            new Value("Fearless"),
            new Value("Feelings"),
            new Value("Ferocious"),
            new Value("Fidelity"),
            new Value("Focus"),
            new Value("Foresight"),
            new Value("Fortitude"),
            new Value("Freedom"),
            new Value("Friendship"),
            new Value("Fun"),
            new Value("Generosity"),
            new Value("Genius"),
            new Value("Giving"),
            new Value("Goodness"),
            new Value("Grace"),
            new Value("Gratitude"),
            new Value("Greatness"),
            new Value("Growth"),
            new Value("Happiness"),
            new Value("Hard"),
            new Value("work Harmony"),
            new Value("Health")
    );
}
