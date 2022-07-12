package hr.bornast.fantasy.application.service.impl;

import java.util.HashSet;
import java.util.Set;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.mapper.UserMapper;
import hr.bornast.fantasy.application.repository.CardRepository;
import hr.bornast.fantasy.application.repository.CoachRepository;
import hr.bornast.fantasy.application.repository.EntityTypeRepository;
import hr.bornast.fantasy.application.repository.FormationRepository;
import hr.bornast.fantasy.application.repository.LeagueRepository;
import hr.bornast.fantasy.application.repository.MediaTypeRepository;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.PositionRepository;
import hr.bornast.fantasy.application.repository.PresidentRepository;
import hr.bornast.fantasy.application.repository.RefereeRepository;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.repository.SeasonRepository;
import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.application.repository.TransferRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.UserService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    // TODO: remove asap
    private final MediaTypeRepository mediaTypeRepository;
    private final EntityTypeRepository entityTypeRepository;
    private final PresidentRepository presidentRepository;
    private final CoachRepository coachRepository;
    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;
    private final PlayerRepository playerRepository;
    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;
    private final FormationRepository formationRepository;
    private final LeagueRepository leagueRepository;
    private final RefereeRepository refereeRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public PagedListDto<UserDto> findAll(Pageable paging, String username) {
        if (username == null) {
            return new PagedListDto<UserDto>().getPagedResult(
                userRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<UserDto>().getPagedResult(
            userRepository.findByUsername(username, paging)
                .map(mapper::map));
    }

    @Override
    public UserDto findOne(int id) {
        return userRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public UserDto create(RegisterCommand command) {
        userRepository.findByUsername(command.getUsername())
            .ifPresent(r -> {throw new ValidationException("User", "user " + command.getUsername() + " already exists");});

        var user = mapper.map(command);

        var role = roleRepository.findByName("User").orElseThrow(RuntimeException::new);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        return mapper.map(userRepository.create(user));
    }

    @Override
    public UserDto update(int id, UpdateUserCommand command) {
        var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, user);
        return mapper.map(userRepository.update(user));
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    // TODO: this is temporary
//    @Transactional
    public void initRolesAndUser() {
//        var adminRole = new Role();
//        adminRole.setName("Admin");
//        var adminRoleCreated = roleRepository.create(adminRole);
//
//        var userRole = new Role();
//        userRole.setName("User");
//        var userRoleCreated = roleRepository.create(userRole);
//
//        var adminUser = new User();
//        adminUser.setFirstName("admin");
//        adminUser.setLastName("admin");
//        adminUser.setUsername("admin");
//        adminUser.setPassword(getEncodedPassword("admin"));
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRoleCreated);
//        adminUser.setRoles(adminRoles);
//        userRepository.create(adminUser);
//
//        var user = new User();
//        user.setFirstName("user");
//        user.setLastName("user");
//        user.setUsername("user");
//        user.setPassword(getEncodedPassword("user"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRoleCreated);
//        user.setRoles(userRoles);
//        userRepository.create(user);
//
//        var entityType = new EntityType();
//        entityType.setName("user");
//        entityTypeRepository.create(entityType);
//
//        var entityType1 = new EntityType();
//        entityType1.setName("player");
//        entityTypeRepository.create(entityType1);
//
//        var entityType2 = new EntityType();
//        entityType2.setName("team");
//        entityTypeRepository.create(entityType2);
//
//        var mediaType = new MediaType();
//        mediaType.setName("image");
//        mediaTypeRepository.create(mediaType);
//
//        var mediaType1 = new MediaType();
//        mediaType1.setName("video");
//        mediaTypeRepository.create(mediaType1);
//
//        var president = new President();
//        president.setName("president");
//        president.setDateOfBirth(new Date());
//        var presidentRes = presidentRepository.create(president);
//
//        var coach = new Coach();
//        coach.setName("coach");
//        coach.setDateOfBirth(new Date());
//        var coachRes = coachRepository.create(coach);
//
//        var stadium = new Stadium();
//        stadium.setName("stadium");
//        var stadiumRes = stadiumRepository.create(stadium);
//
//        var position = new Position();
//        position.setName("position");
//        var positionRes = positionRepository.create(position);
//
//        var team = new Team();
//        team.setName("hajduk");
//        team.setDateOfEstablishment(new Date());
//        team.setPresident(presidentRes);
//        team.setCoach(coachRes);
//        team.setStadium(stadiumRes);
//        var teamRes = teamRepository.create(team);
//
//        var team1 = new Team();
//        team1.setName("sibenik");
//        team1.setDateOfEstablishment(new Date());
//        team1.setPresident(presidentRes);
//        team1.setCoach(coachRes);
//        team1.setStadium(stadiumRes);
//        var teamRes1 = teamRepository.create(team1);
//
//        var player = new Player();
//        player.setName("Livaja");
//        player.setDateOfBirth(new Date());
//        player.setPosition(positionRes);
//        var playerRes = playerRepository.create(player);
//
//        var player1 = new Player();
//        player1.setName("Kalinic");
//        player1.setDateOfBirth(new Date());
//        player1.setPosition(positionRes);
//        var playerRes1 = playerRepository.create(player1);
//
//        var yellowCard = new Card();
//        yellowCard.setName("yellow");
//        var yellowCardRes = cardRepository.create(yellowCard);
//
//        var redCard = new Card();
//        redCard.setName("red");
//        var redCardRes = cardRepository.create(redCard);
//
//        var player2 = new Player();
//        player2.setName("Sahiti");
//        player2.setDateOfBirth(new Date());
//        player2.setPosition(positionRes);
//        var playerRes2 = playerRepository.create(player2);
//
//        var player3 = new Player();
//        player3.setName("Krovinovic");
//        player3.setDateOfBirth(new Date());
//        player3.setPosition(positionRes);
//        var playerRes3 = playerRepository.create(player3);
//
//        var player4 = new Player();
//        player4.setName("Grgic");
//        player4.setDateOfBirth(new Date());
//        player4.setPosition(positionRes);
//        var playerRes4 = playerRepository.create(player4);
//
//        var player5 = new Player();
//        player5.setName("Elez");
//        player5.setDateOfBirth(new Date());
//        player5.setPosition(positionRes);
//        var playerRes5 = playerRepository.create(player5);
//
//        var player6 = new Player();
//        player6.setName("Meljnak");
//        player6.setDateOfBirth(new Date());
//        player6.setPosition(positionRes);
//        var playerRes6 = playerRepository.create(player6);
//
//        var transfer = new Transfer();
//        transfer.setFromTeam(teamRes);
//        transfer.setToTeam(teamRes1);
//        transfer.setPlayer(playerRes);
//        transfer.setTransferDate(new Date());
//        transferRepository.create(transfer);
//
//        var transfer1 = new Transfer();
//        transfer1.setFromTeam(teamRes);
//        transfer1.setToTeam(teamRes1);
//        transfer1.setPlayer(playerRes1);
//        transfer1.setTransferDate(new Date());
//        transferRepository.create(transfer1);
//
//        var transfer2 = new Transfer();
//        transfer2.setFromTeam(teamRes);
//        transfer2.setToTeam(teamRes1);
//        transfer2.setPlayer(playerRes2);
//        transfer2.setTransferDate(new Date());
//        transferRepository.create(transfer2);
//
//        var transfer3 = new Transfer();
//        transfer3.setFromTeam(teamRes1);
//        transfer3.setToTeam(teamRes);
//        transfer3.setPlayer(playerRes3);
//        transfer3.setTransferDate(new Date());
//        transferRepository.create(transfer3);
//
//        var transfer4 = new Transfer();
//        transfer4.setFromTeam(teamRes1);
//        transfer4.setToTeam(teamRes);
//        transfer4.setPlayer(playerRes4);
//        transfer4.setTransferDate(new Date());
//        transferRepository.create(transfer4);
//
//        var transfer5 = new Transfer();
//        transfer5.setFromTeam(teamRes1);
//        transfer5.setToTeam(teamRes);
//        transfer5.setPlayer(playerRes5);
//        transfer5.setTransferDate(new Date());
//        transferRepository.create(transfer5);
//
//        var transfer6 = new Transfer();
//        transfer6.setFromTeam(teamRes1);
//        transfer6.setToTeam(teamRes);
//        transfer6.setPlayer(playerRes6);
//        transfer6.setTransferDate(new Date());
//        transferRepository.create(transfer6);
//
//        var formation = new Formation();
//        formation.setName("4-4-2");
//        var formationRes = formationRepository.create(formation);
//
//        var season = new Season();
//        season.setName("2021/2022");
//        season.setStartDate(new Date());
//        season.setEndDate(new Date());
//        var seasonRes = seasonRepository.create(season);
//
//        var league = new League();
//        league.setSeason(seasonRes);
//        league.setName("1.HNL");
//        Set<Team> teamList = new HashSet<>();
//        teamList.add(teamRes);
//        teamList.add(teamRes1);
//        league.setTeams(teamList);
//        var leagueRes = leagueRepository.create(league);
//
//        var referee = new Referee();
//        referee.setName("Sudac");
//        referee.setDateOfBirth(new Date());
//        var refereeRes = refereeRepository.create(referee);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
