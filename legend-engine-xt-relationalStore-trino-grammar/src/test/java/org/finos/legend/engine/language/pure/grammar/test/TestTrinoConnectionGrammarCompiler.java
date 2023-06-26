// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.language.pure.grammar.test;

import org.antlr.v4.runtime.Vocabulary;
import org.finos.legend.engine.language.pure.compiler.test.TestCompilationFromGrammar;
import org.junit.Test;

public class TestTrinoConnectionGrammarCompiler
        extends TestCompilationFromGrammar.TestCompilationFromGrammarTestSuite
{
    @Test
    public void testConnectionGrammar()
    {
        test("###Connection\n" +
                "RelationalDatabaseConnection simple::TrinoConnection\n" +
                "{\n" +
                "  store: apps::pure::studio::relational::tests::dbInc;\n" +
                "  type: Trino;\n" +
                "  specification: Trino\n" +
                "  {\n" +
                "    host: 'host';\n" +
                "    port: 1234;\n" +
                "    catalog: 'tpch';\n" +
                "    schema: 'tiny';\n" +
                "    clientTags: 'cg::vega';\n" +
                "    sslSpecification:\n" +
                "    {\n" +
                "      ssl: false;\n" +
                "      trustStorePathVaultReference: 'abc12cde';\n" +
                "      trustStorePasswordVaultReference: 'abc12cde';\n" +
                "    };\n" +
                "  };\n" +
                "  auth: TrinoDelegatedKerberos\n" +
                "  {\n" +
                "    kerberosUseCanonicalHostname: false;\n" +
                "    kerberosRemoteServiceName: 'HTTP';\n" +
                "  };\n" +
                "}\n",null);
    }

    @Override
    protected String getDuplicatedElementTestCode()
    {
        return "";
    }

    @Override
    protected String getDuplicatedElementTestExpectedErrorMessage()
    {
        return null;
    }

    @Test
    public void testGetParserErrorWrongProperty()
    {
        test("###TrinoApp\n" +
                "TrinoApp x::A\n" +
                "{\n" +
                "   applicatioName : 'sass';\n" +
                "}\n", "PARSER error at [4:4-17]: Unexpected token 'applicatioName'. Valid alternatives: ['applicationName', 'description', 'function', 'owner']");
    }

    @Test
    public void testGetParserErrorMissingApplicationName()
    {
        test("###Trino\n" +
                "TrinoApp x::A\n" +
                "{\n" +
                "   owner : 'pierre';\n" +
                "}\n", "PARSER error at [2:1-5:1]: Field 'applicationName' is required");
    }

    @Test
    public void testGetParserErrorMissingFunction()
    {
        test("###Trino\n" +
                "TrinoApp x::A\n" +
                "{\n" +
                "   applicationName : 'MyApp';\n" +
                "   owner : 'pierre';\n" +
                "}\n", "PARSER error at [2:1-6:1]: Field 'function' is required");
    }
}

