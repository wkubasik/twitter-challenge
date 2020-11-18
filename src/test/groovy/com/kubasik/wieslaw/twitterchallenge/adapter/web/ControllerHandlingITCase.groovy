package com.kubasik.wieslaw.twitterchallenge.adapter.web

import com.kubasik.wieslaw.twitterchallenge.exceptions.UsernameNotFoundException
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ControllerHandlingITCase extends Specification {

    private static final String USERNAME = "adam"

    @Autowired
    MockMvc mockMvc

    @SpringBean
    UsersApiService apiService = Mock()

    def "should return not found when username not found thrown"() {
        given:
            apiService.getMyTweets(USERNAME) >> { throw new UsernameNotFoundException(USERNAME) }

        when:
            def result = getTweets()

        then:
            result.andExpect(status().isNotFound())
    }

    @Unroll
    def "should return bad request when #exception is thrown"(Exception exception) {
        given:
            apiService.getMyTweets(USERNAME) >> { throw exception }

        when:
            def result = getTweets()

        then:
            result.andExpect(status().isBadRequest())

        where:
            exception                            | _
            getConstraintViolationException()    | _
            getMethodArgumentNotValidException() | _
    }

    def "should return internal server exception when unknown runtime exception is thrown"() {
        given:
            apiService.getMyTweets(USERNAME) >> { throw new RuntimeException() }

        when:
            def result = getTweets()

        then:
            result.andExpect(status().isInternalServerError())
    }

    private ResultActions getTweets() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{username}/tweets", USERNAME))
    }

    private static ConstraintViolationException getConstraintViolationException() {
        new ConstraintViolationException(null)
    }

    private static MethodArgumentNotValidException getMethodArgumentNotValidException() {
        new MethodArgumentNotValidException(null, new BindException("", ""))
    }

}
